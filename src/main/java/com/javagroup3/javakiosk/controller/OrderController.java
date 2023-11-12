package com.javagroup3.javakiosk.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javagroup3.javakiosk.ResponseTransfer;
import com.javagroup3.javakiosk.config.CurrentMember;
import com.javagroup3.javakiosk.dto.Cart;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.entity.OrderRecord;
import com.javagroup3.javakiosk.entity.Product;
import com.javagroup3.javakiosk.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ProductService productService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/order") // 메인 페이지 주소
    public String order(Model model){
        List<Product> productList = this.productService.getList();
        model.addAttribute(productList);

        return "order";
    }

    /**
     *
     * @param cart JSON 타입의 장바구니 객체 배열
     * @param authentication Spring Security 의 인증 관련 객체.
     */
    @PostMapping("/order")
    @ResponseBody
    @Transactional
    public ResponseTransfer order(@RequestBody String cart, Authentication authentication){

        System.out.println(cart);

        // GSON 으로 JSON 형태의 문자열을 비직렬화 합니다.
        Gson gson = new Gson();

        // cart 는 배열 형태이므로 아래와 같이 Type 을 정의합니다.
        Type listOfMyClassObject = new TypeToken<ArrayList<Cart>>() {}.getType();
        List<Cart> cartDTO = gson.fromJson(cart, listOfMyClassObject);

        // 여러 행을 삽입하기 위해 entityManager 의 persist 메소드를 사용합니다.
        List<OrderRecord> orderRecords = new ArrayList<>();

        for(Cart cartItem : cartDTO){
            // 우선 OrderRecord 에 product_id 와 member_id 가 외래키로서 필요하기 때문에
            // entityManager 를 통해 각 테이블을 Proxy 로 참조합니다.
            // 이렇게 하면 아래 entityManager.getReference 로 가져을 때는 실제 DB 참조가 이루어지지 않고
            // orderRecord 가 영속화 될 때 실제 쿼리가 실행됩니다.

            CurrentMember currentMember = (CurrentMember) authentication.getPrincipal();
            Member member = entityManager.getReference(Member.class, currentMember.getId());
            Product product = entityManager.getReference(Product.class, cartItem.getProductId());

            OrderRecord orderRecord = OrderRecord.builder()
                    .member(member)
                    .product(product)
                    .orderQuantity(cartItem.getQuantity())  // 적절한 방식으로 주문 수량을 가져오세요.
                    .orderDate(LocalDateTime.now())
                    .build();
            orderRecords.add(orderRecord);
        }
        for(OrderRecord orderRecord : orderRecords){
            entityManager.persist(orderRecord);
        }

        entityManager.flush();

        return new ResponseTransfer("주문이 성공적으로 처리되었습니다.");
    }
}