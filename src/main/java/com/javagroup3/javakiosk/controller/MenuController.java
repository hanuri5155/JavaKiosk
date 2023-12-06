package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.ProductDTO;
import com.javagroup3.javakiosk.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private  final ProductService productService;
    @GetMapping("/menu") // 메뉴 페이지
    public String menu(Model model){
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("products", productDTOList);
        return "menu";
    }
}