package com.javagroup3.javakiosk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id; // 회원 아이디

    private String nickname; // 유저 닉네임
    private String username; // 유저 이름
    private String password; // 비밀번호
    private String email; // 이메일
    private String phone_number; // 전화번호
    private int age; // 나이
    private LocalDateTime join_date; // 가입일
    private int point; // 적립금
}
