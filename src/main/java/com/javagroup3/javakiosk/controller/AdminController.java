package com.javagroup3.javakiosk.controller;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.dto.ProductDTO;
import com.javagroup3.javakiosk.entity.OrderRecord;
import com.javagroup3.javakiosk.service.MemberService;
import com.javagroup3.javakiosk.service.OrderRecordService;
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
    private  final MemberService memberService;
    private final OrderRecordService orderRecordService;

    @GetMapping("/admins")
    public String orderManagement(Model model){
        List<OrderRecord> orderRecords = orderRecordService.getList();

        System.out.println(orderRecords);

        model.addAttribute("orderRecords", orderRecords);
        return "admins/orderManagement";
    }

    private  final ProductService productService;
    @GetMapping("/admins/productManagement")
    public String productFindAll(Model model){ // 제품 출력
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("productlist", productDTOList);
        return "admins/productManagement";
    }
    @GetMapping("/admins/memberManagement")
    public String memberFindAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있을시 그 객체를 실어나르는 역할을 하는게 model
        model.addAttribute("memberlist", memberDTOList);
        return "admins/memberManagement";
    }

    @GetMapping("/products/{id}") // 제품 상세 조회
    public String productFindyId(@PathVariable Integer id, Model model){
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("products", productDTO);
        return "products/productDetail";
    }

    @GetMapping("/products/productUpdate/{id}") // 제품 정보 수정
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
