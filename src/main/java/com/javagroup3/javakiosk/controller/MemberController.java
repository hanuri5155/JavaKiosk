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

    // private final MemberRepository memberRepository;
    // private final MemberSecurityService memberSecurityService;

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "members/login";
    }

    @GetMapping("/signUp")
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

//    @GetMapping("/login") // 로그인
//    public String MemberForm(@ModelAttribute("MemberForm") MemberDTO memberDTO) {return "members/login";}
//
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute("MemberForm") MemberDTO memberDTO, BindingResult
//            bindingResult, HttpSession session) {
//        /*if (bindingResult.hasErrors()) {
//            System.out.println("바인딩 에러");
//            return "members/login";
//        }*/
//
//        MemberDTO loginMember = memberSecurityService.login(memberDTO);
//
//        if (loginMember == null) { // 로그인 실패
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            System.out.println("로그인 실패");
//            return "members/login";
//        } else{
//            // 로그인 성공
//            session.setAttribute("nickname", loginMember.getNickname());
//            System.out.println("로그인 성공");
//            return "order";
//            //return "redirect:/";
//        }
//    }
//
//    @GetMapping("/signup") // 회원가입
//    // @ModelAttribute 어노테이션으로 member 를 지정하면 타임리프와 같은 템플릿 엔진에서
//    // 해당 이름으로 Member 객체를 참조할 수 있습니다.
//
//    // 이때 ModelAttribute 로 Entity 를 반환하면 데이터베이스의 모델이 view 로 노출될 수 있습니다.
//    // 따라서 별도의 DTO 를 생성하여 반환합니다.
//
//    public String addForm(@ModelAttribute("member") MemberDTO memberDTO) {
//        return "members/signUp";
//    }
//
//    @PostMapping("/signup")
//    public String save(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "members/signUp";
//        }
//        memberRepository.save(Member.toEntity(memberDTO));
//        return "redirect:/";
//    }
}