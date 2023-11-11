package com.javagroup3.javakiosk.entity;

import com.javagroup3.javakiosk.dto.AdminDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    private String adminNickname; // 관리자 닉네임
    private String adminUsername; // 관리자 이름
    private String adminPassword; // 관리자 비밀번호

    private LocalDateTime admin_join_date; // 관리자 가입일

    @PrePersist
    protected void onCreate() {
        admin_join_date = LocalDateTime.now(); // 현재 시간을 가입 날짜로 설정
    }

    public static Admin toEntity(AdminDTO dto){
        // Repository로 DB 작업을 할때는 Entity 객체를 넘겨 줘야 하므로 DTO 객체를 Entity객체로 변환하는 메소드
        return Admin.builder()
                .adminNickname(dto.getAdminNickname())
                .adminUsername(dto.getAdminUsername())
                .adminPassword(dto.getAdminPassword())
                .build();
    }
}
