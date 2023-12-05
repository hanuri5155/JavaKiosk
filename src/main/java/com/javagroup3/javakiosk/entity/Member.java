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

    // Member 엔티티가 DB 에 저장되기 직전에 값을 생성합니다.
    @PrePersist
    protected void onCreate() {
        join_date = LocalDateTime.now(); // 현재 시간을 가입 날짜로 설정
        point = 0;
    }

    // DTO 와 엔티티 간 변환이 필요한 이유는
    // DB 를 있는 그대로 표현하는 Entity 가 그대로 노출되는 것을 막기 위함.
    // 그리고 Entity 는 DB 의 모델이기 때문에 setter 를 통해 비즈니스 로직에 맞게 수정되어야 하는 경우가 있는데
    // 이렇게 되면 Entity 와 DB 의 불일치, 즉 무결성이 깨질 수 있다.
    // https://velog.io/@pjy05200/DTO%EC%99%80-Entity-Class%EC%9D%98-%EC%B0%A8%EC%9D%B4

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