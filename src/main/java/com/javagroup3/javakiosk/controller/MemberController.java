package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login") // 로그인 페이지
    public String login(){
        return "members/login";
    }

    @GetMapping("/signUp") // 회원 가입 페이지
    public String signup(@ModelAttribute("member") MemberDTO memberDTO){
        return "members/signUp";
    }

    @PostMapping("/signUp")
    public String save(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/signUp";
        }
        memberService.create(
                memberDTO.getUsername(),
                memberDTO.getPassword(),
                memberDTO.getEmail(),
                memberDTO.getPhone_number(),
                memberDTO.getAge()
                );
        return "redirect:/";
    }
}