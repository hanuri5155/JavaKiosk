package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.dto.ProductDTO;
import com.javagroup3.javakiosk.service.MemberService;
import com.javagroup3.javakiosk.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public String productFindAll(Model model){ // 제품 출력
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("productlist", productDTOList);
        return "admins/productManagement";
    }

    @GetMapping("/products/{id}")
    public String productFindyId(@PathVariable Integer id, Model model){
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("products", productDTO);
        return "products/productDetail";
    }

    @GetMapping("/products/productUpdate/{id}")
    public String productUpdateForm(@PathVariable Integer id, Model model){
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("products", productDTO);
        return "products/productUpdate";
    }

    @PostMapping("/products/productUpdate/{id}")
    public String productUpdate(@ModelAttribute ProductDTO productDTO){
        productService.ubdate(productDTO);
        return "redirect:/products/" + productDTO.getProduct_id();
    }
}
