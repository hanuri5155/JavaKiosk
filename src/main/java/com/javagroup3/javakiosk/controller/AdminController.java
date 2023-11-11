package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.dto.ProductDTO;
import com.javagroup3.javakiosk.service.MemberService;
import com.javagroup3.javakiosk.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/admins")
public class AdminController {
    //@RequestMapping("/")
    /*@GetMapping("/admins")
    public String admin(){
        return "admins/memberManagement";
    }*/
    private  final MemberService memberService;
    @GetMapping("/admins")
    public String memberFindAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있을시 그 객체를 실어나르는 역할을 하는게 model
        model.addAttribute("memberlist", memberDTOList);
        return "admins/memberManagement";
    }

    private  final ProductService productService;
    @GetMapping("/admins/productManagement")
    public String productFindAll(Model model){
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("productlist", productDTOList);
        return "admins/productManagement";
    }
}
