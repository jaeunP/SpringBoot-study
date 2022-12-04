package com.example.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {

    @GetMapping("hi")
    public String niceTMeetYou(Model model) {
        model.addAttribute("username","재언");
        return "greetings.html";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","jaeun");
        return "goodbye.html";
    }
}