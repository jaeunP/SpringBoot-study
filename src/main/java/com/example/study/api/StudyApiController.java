package com.example.study.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Rest Api용 컨트롤러, JSON 반환
public class StudyApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}