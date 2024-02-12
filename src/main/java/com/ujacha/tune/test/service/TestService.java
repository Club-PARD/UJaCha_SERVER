package com.ujacha.tune.test.service;

import com.ujacha.tune.auth.jwt.TokenProvider;
import com.ujacha.tune.member.repository.MemberRepository;
import com.ujacha.tune.test.domain.TestEntity;
import com.ujacha.tune.test.dto.TestRequestDTO;
import com.ujacha.tune.test.dto.TestResponseDTO;
import com.ujacha.tune.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public TestResponseDTO.Response testToResult(TestRequestDTO dto, String jwt) {
        TestEntity test = testRepository.save(TestEntity.testToResult(answerToTest(dto),
                memberRepository.findByUid(tokenProvider.validate(jwt)).orElseThrow()));
        return TestResponseDTO.Response.toDto(test);
    }

    public TestResponseDTO.First firstTest(TestRequestDTO dto) {
        return TestResponseDTO.First.first(answerToTest(dto));
    }

    public TestResponseDTO.Symptom answerToTest(TestRequestDTO dto) {
        TestResponseDTO.Symptom symptom = new TestResponseDTO.Symptom();
        symptom.setHallucination(testAverage(dto.getQuestion1(),dto.getQuestion2(),dto.getQuestion3()));
        symptom.setAbnormalBehavior(testAverage(dto.getQuestion4(),dto.getQuestion5(),dto.getQuestion6()));
        symptom.setMoody(testAverage(dto.getQuestion7(),dto.getQuestion8(),dto.getQuestion9()));
        symptom.setDelusion(testAverage(dto.getQuestion10(),dto.getQuestion11(),dto.getQuestion12()));
        return symptom;
    }
    public int testAverage(int question1, int question2, int question3) {
        return (answer(question1) * 33 +
                answer(question2) * 33 +
                answer(question3) * 33 )
                / 10;
    }
    public int answer(int question){
        return switch (question) {
            case 1 -> 0;
            case 2 -> 3;
            case 3 -> 5;
            case 4 -> 7;
            default -> 10;
        };
    }

    public List<TestResponseDTO.Response> listTestEntityToDTo(String jwt) {
        Long memberId = memberRepository.findByUid(tokenProvider.validate(jwt)).orElseThrow().getId();
        return testRepository.findByMemberId(memberId)
                .stream()
                .map(TestResponseDTO.Response::toDto)
                .collect(Collectors.toList());
    }

    public TestResponseDTO.Response firstResultToSave(TestResponseDTO.First dto, String jwt) {
        TestEntity test = testRepository.save(TestEntity.testToResult(dto,
                memberRepository.findByUid(tokenProvider.validate(jwt)).orElseThrow()));
        return TestResponseDTO.Response.toDto(test);
    }

}
