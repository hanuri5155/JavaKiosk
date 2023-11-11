package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
// @RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
