package com.ujacha.tune.test.service;

import com.ujacha.tune.member.service.core.MemberService;
import com.ujacha.tune.test.dto.TestRequestDTO;
import com.ujacha.tune.test.dto.TestResponseDTO;
import com.ujacha.tune.test.service.core.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestFacade {
    private final MemberService memberService;
    private final TestService testService;

    @Transactional
    public TestResponseDTO.Response testToResult(TestRequestDTO dto, String jwt) {
        if (testService.existsByDateAndMember(memberService.findByJwt(jwt))) {
            testService.deleteByDateAndMember(memberService.findByJwt(jwt));}
        return TestResponseDTO.Response.toDto(testService.save(dto, memberService.findByJwt(jwt)));
    }
    public TestResponseDTO.First firstTest(TestRequestDTO dto) {
        return TestResponseDTO.First.first(testService.answerToTest(dto));
    }
    public TestResponseDTO.Response firstResultToSave(TestResponseDTO.First dto, String jwt) {
        return TestResponseDTO.Response.toDto(testService.save(dto, memberService.findByJwt(jwt)));
    }

    public Boolean existsByDateAndMember(String jwt) {
        return testService.existsByDateAndMember(memberService.findByJwt(jwt));
    }

}
