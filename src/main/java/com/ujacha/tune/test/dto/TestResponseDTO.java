package com.ujacha.tune.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ujacha.tune.test.domain.TestEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class TestResponseDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Symptom {
        //환각, 환청
        private int hallucination;
        //이상행동
        private int abnormalBehavior;
        //감정변화
        private int moody;
        //망상
        private int delusion;
    }

    @Getter
    @Setter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
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
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate date;

        public static Response toDto(final TestEntity test) {
            return Response.builder()
                    .testId(test.getTestId())
                    .hallucination(test.getHallucination())
                    .abnormalBehavior(test.getAbnormalBehavior())
                    .moody(test.getMoody())
                    .delusion(test.getDelusion())
                    .total(test.getTotal())
                    .date(test.getDate())
                    .build();
        }

    }

    @Getter
    @Builder
    public static class First {
        //환각, 환청
        private int hallucination;
        //이상행동
        private int abnormalBehavior;
        //감정변화
        private int moody;
        //망상
        private int delusion;

        private int total;
        public static First first(final TestResponseDTO.Symptom dto) {
            return First.builder()
                    .hallucination(dto.getHallucination())
                    .abnormalBehavior(dto.getAbnormalBehavior())
                    .moody(dto.getMoody())
                    .delusion(dto.getDelusion())
                    .total((dto.getHallucination() + dto.getAbnormalBehavior() + dto.getDelusion() + dto.getMoody()) / 4)
                    .build();
        }
    }


}
