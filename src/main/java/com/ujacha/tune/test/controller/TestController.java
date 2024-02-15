package com.ujacha.tune.test.controller;

import com.ujacha.tune.test.dto.TestRequestDTO;
import com.ujacha.tune.test.dto.TestResponseDTO;
import com.ujacha.tune.test.service.TestFacade;
import com.ujacha.tune.test.service.core.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Test 컨트롤러", description = "테스트 결과 조회 컨트롤러입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final TestFacade testFacade;

    @Operation(summary = "로그인 하고 난 후 Test",
            description = "로그인 후 jwt토큰과 테스트 정답을 받아야지 저장이 됩니다. Id와 Date를 포함한 결과를 반환합니다. ")
    @PostMapping("")
    public TestResponseDTO.Response testToResult(@RequestBody final TestRequestDTO dto,
                                                 @RequestHeader(value = "Authorization", required = false) final String jwt) {
        return testFacade.testToResult(dto, jwt);
    }

    @Operation(summary = "로그인 하기 전 첫 번째 Test",
            description = "테스트의 답을 받고 난 후 결과만 반환하고 데이터베이스에 저장하지는 않습니다.")
    @PostMapping("/first")
    public TestResponseDTO.First testToResult(@RequestBody final TestRequestDTO dto) {
        return testFacade.firstTest(dto);
    }

    @Operation(summary = "로그인 한 후 첫 번째 Test",
            description = "결과와 jwt를 받고 그를 데이터베이스에 저장합니다.")
    @PostMapping("/first/save")
    public TestResponseDTO.Response firstResultToSave(@RequestBody final TestResponseDTO.First dto, @RequestHeader(value = "Authorization") String jwt) {
        return testFacade.firstResultToSave(dto, jwt);
    }
    @Operation(summary ="오늘 테스트 했나? 안했나",
            description = "오늘 테스트를 했을경우 :True, 안 했을 경우:False")
    @GetMapping("/exist-today")
    public Boolean existsByDate(@RequestHeader(value = "Authorization") final String jwt) {
        return testFacade.existsByDateAndMember(jwt);
    }


}
