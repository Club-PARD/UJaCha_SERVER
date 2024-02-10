package com.ujacha.tune.mock;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock")
public class MockController {
    @GetMapping("/test")
    public ResponseDTO test() {
        return new ResponseDTO(1,
                2,3,
                4,
                15,LocalDate.now());
    }
}
