package com.javagroup3.javakiosk.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Cart {
    private int productId; // 제품 아이디
    private String productName; // 제품 이름
    private int productPrice; // 제품 가격
    private int quantity; // 주문 수량
}
