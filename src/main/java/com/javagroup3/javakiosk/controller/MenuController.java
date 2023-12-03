package com.javagroup3.javakiosk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @GetMapping("/menu") // 메인 페이지 주소
    public String menu(){
        return "menu";
    }
}
