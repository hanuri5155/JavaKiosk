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

// 컨트롤러는 HTTP 요청을 처리하는 레이어입니다.
// 각 메소드는 Service 클래스의 메소드를 호출하여 필요한 로직을 실행하고 응답을 생성합니다.
// 여기서는 Thymeleaf 라는 템플릿 엔진에 DTO 를 전달하여 페이지를 생성할 수 있도록 합니다.

@Controller
@RequiredArgsConstructor
public class AdminController {
    private  final MemberService memberService;
    private final OrderRecordService orderRecordService;

    @GetMapping("/admins") // 주문 관리 페이지
    public String orderManagement(Model model){
        List<OrderRecord> orderRecords = orderRecordService.getList();

        System.out.println(orderRecords);

        model.addAttribute("orderRecords", orderRecords);
        return "admins/orderManagement";
    }

    private  final ProductService productService;
    @GetMapping("/admins/productManagement") // 제품 관리 페이지
    public String newproductFindAll(Model model){ // 제품 출력
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("productlist", productDTOList);
        return "admins/productManagement";
    }
    @GetMapping("/admins/memberManagement") // 회원 관리 페이지
    public String newmemberFindAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있을시 그 객체를 실어나르는 역할을 하는게 model
        model.addAttribute("memberlist", memberDTOList);
        return "admins/memberManagement";
    }

    @GetMapping("/products/{id}") // 제품 상세 조회 및 수정 페이지
    public String productFindId(@PathVariable Integer id, Model model){
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("products", productDTO);
        return "products/productDetail";
    }

    @PostMapping("/products/{id}")
    public String productUpdate(@ModelAttribute ProductDTO productDTO){
        productService.ubdate(productDTO);
        return "redirect:/products/" + productDTO.getProduct_id();
    }
}