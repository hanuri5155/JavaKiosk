package com.javagroup3.javakiosk.dto;

import com.javagroup3.javakiosk.entity.Product;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private Boolean is_orderable; // 주문 가능 여부
    private Integer price; // 가격
    private String name; // 음식이름

    public static ProductDTO toDTO(Product entity){
        return ProductDTO.builder()
                .is_orderable(entity.is_orderable())
                .price(entity.getPrice())
                .name(entity.getName())
                .build();
    }
}