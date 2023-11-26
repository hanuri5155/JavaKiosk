package com.javagroup3.javakiosk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController { // wereqw
    // 메인 페이지 요청
    @GetMapping("/") // 메인 페이지 주소
    public String index(){
        return "home";
    }
    @GetMapping("/mainpage") // 메인 페이지 주소
    public String index1() {
        return "mainpage";
    }
    @GetMapping("/mainpage2") // 메인 페이지 주소
    public String index2() {
        return "mainpage2";
    }
}
