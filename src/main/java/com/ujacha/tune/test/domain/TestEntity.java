package com.ujacha.tune.test.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ujacha.tune.member.domain.Member;
import com.ujacha.tune.test.dto.TestResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long testId;
//환각, 환청
    private int hallucination;
//이상행동
    private int abnormalBehavior;
//감정변화
    private int moody;
//망상
    private int delusion;

    private int total;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//    @Column(unique = true)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    public static TestEntity testToResult(TestResponseDTO.Symptom dto, Member member) {
        return TestEntity.builder()
                .hallucination(dto.getHallucination())
                .abnormalBehavior(dto.getAbnormalBehavior())
                .moody(dto.getMoody())
                .delusion(dto.getDelusion())
                .total((dto.getHallucination() + dto.getAbnormalBehavior() + dto.getDelusion() + dto.getMoody()) / 4)
                .member(member)
                .build();
    }

}
