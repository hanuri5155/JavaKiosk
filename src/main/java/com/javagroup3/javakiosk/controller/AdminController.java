package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.AdminDTO;
import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.entity.Admin;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.repository.AdminRepository;
import com.javagroup3.javakiosk.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/add") // 관리자 등록
    public String addForm(@ModelAttribute("admin") AdminDTO adminDTO) {
        return "admins/adminRegister";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute AdminDTO adminDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admins/adminRegister";
        }
        // AdminRepository.save(Admin.toEntity(adminDTO)); // 여기부터~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return "redirect:/";
    }

    @GetMapping("/login") // 로그인
    public String AdminForm(@ModelAttribute("AdminForm") AdminDTO adminDTO) {return "Admin/login";}

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("AdminForm") AdminDTO adminDTO, BindingResult
            bindingResult, HttpSession session) {
        /*if (bindingResult.hasErrors()) {
            System.out.println("바인딩 에러");
            return "members/login";
        }*/
                                                `
        AdminDTO loginAdmin = adminService.login(adminDTO);

        if (loginAdmin == null) { // 로그인 실패
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            System.out.println("로그인 실패");
            return "Admins/login";
        } else{
            // 로그인 성공
            session.setAttribute("nickname", loginAdmin.getAdminNickname());
            System.out.println("로그인 성공");
            return "admins/memberManagement";
        }
    }
}