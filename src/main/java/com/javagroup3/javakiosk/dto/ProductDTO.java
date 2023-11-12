package com.javagroup3.javakiosk.dto;

import com.javagroup3.javakiosk.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private Integer product_id; // 제품 아이디
    private Boolean is_orderable; // 주문 가능 여부
    private Integer price; // 가격
    private String name; // 음식이름

    public static ProductDTO toDTO(Product entity){
        return ProductDTO.builder()
                .product_id(entity.getProduct_id())
                .is_orderable(entity.is_orderable())
                .price(entity.getPrice())
                .name(entity.getName())
                .build();
    }
}