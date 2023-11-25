package com.javagroup3.javakiosk.entity;

import com.javagroup3.javakiosk.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 아이디

    @Column(unique = true)
    private String username; // 유저 이름
    private String password; // 비밀번호
    private String email; // 이메일
    private String phone_number; // 전화번호
    private Integer age; // 나이
    private LocalDateTime join_date; // 가입일
    private Integer point; // 적립금

    @PrePersist
    protected void onCreate() {
        join_date = LocalDateTime.now(); // 현재 시간을 가입 날짜로 설정
        point = 0;
    }

    public static Member toEntity(MemberDTO dto){
        // Repository로 DB 작업을 할때는 Entity 객체를 넘겨 줘야 하므로 DTO 객체를 Entity객체로 변환하는 메소드
        return Member.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phone_number(dto.getPhone_number())
                .age(dto.getAge())
                .join_date(dto.getJoin_date())
                .build();
    }
}