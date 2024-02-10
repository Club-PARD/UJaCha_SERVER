package com.ujacha.tune.test.controller;

import com.ujacha.tune.test.dto.TestRequestDTO;
import com.ujacha.tune.test.dto.TestResponseDTO;
import com.ujacha.tune.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final TestService testService;

    @PostMapping("")
    public TestResponseDTO.Response testToResult(@RequestBody TestRequestDTO dto) {
        return testService.testToResult(dto);
    }
}
