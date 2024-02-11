package com.ujacha.tune.test.controller;

import com.ujacha.tune.auth.jwt.TokenProvider;
import com.ujacha.tune.test.dto.TestRequestDTO;
import com.ujacha.tune.test.dto.TestResponseDTO;
import com.ujacha.tune.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final TestService testService;
    private final TokenProvider tokenProvider;

    @PostMapping("")
    public TestResponseDTO.Response testToResult(@RequestBody TestRequestDTO dto,
                               @RequestHeader(value = "Authorization", required = false) String jwt) {
        return testService.testToResult(dto,jwt);
    }
    @GetMapping("/first")
    public TestResponseDTO.Response testToResult(@RequestBody TestRequestDTO dto) {
        return testService.firstTest(dto);
    }
}
