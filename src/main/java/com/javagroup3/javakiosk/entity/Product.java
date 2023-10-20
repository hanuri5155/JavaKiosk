package com.javagroup3.javakiosk.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id; // 회원 아이디

    private String name; // 제품 이름
    private int price; // 제품 가격
    private boolean is_orderable; // 주문 가능 여부
}
