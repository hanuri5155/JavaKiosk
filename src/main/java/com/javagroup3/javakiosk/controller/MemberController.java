package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.repository.MemberRepository;
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

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    // @ModelAttribute 어노테이션으로 member 를 지정하면 타임리프와 같은 템플릿 엔진에서
    // 해당 이름으로 Member 객체를 참조할 수 있습니다.

    // 이때 ModelAttribute 로 Entity 를 반환하면 데이터베이스의 모델이 view 로 노출될 수 있습니다.
    // 따라서 별도의 DTO 를 생성하여 반환합니다.

    public String addForm(@ModelAttribute("member") MemberDTO memberDTO) {
        return "members/joinMember";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/joinMember";
        }

        memberRepository.save(Member.toEntity(memberDTO));
        return "redirect:/";
    }

}
