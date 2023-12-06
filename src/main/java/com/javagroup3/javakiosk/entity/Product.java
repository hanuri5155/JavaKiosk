package com.javagroup3.javakiosk.entity;

import com.javagroup3.javakiosk.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id; // 제품 아이디

    private String name; // 제품 이름
    private int price; // 제품 가격
    private boolean is_orderable; // 주문 가능 여부
    private String description; // 제품 설명
    private String image_path; // 제품 사진 경로
    private String category;

    @PrePersist
    protected void onCreate() {
        is_orderable = true;
    }

    public static Product toUpdateProductEntity(ProductDTO dto){
        // Repository로 DB 작업을 할때는 Entity 객체를 넘겨 줘야 하므로 DTO 객체를 Entity객체로 변환하는 메소드
        return Product.builder()
                .product_id(dto.getProduct_id())
                .name(dto.getName())
                .price(dto.getPrice())
                .is_orderable(dto.getIs_orderable())
                .description(dto.getDescription())
                .image_path(dto.getImage_path())
                .category(dto.getCategory())
                .build();
    }
}