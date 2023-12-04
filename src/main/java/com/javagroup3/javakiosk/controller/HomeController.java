package com.javagroup3.javakiosk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 메인 페이지 요청
    @GetMapping("/") // 메인 페이지 주소
    public String index(){
        return "home";
    }
}