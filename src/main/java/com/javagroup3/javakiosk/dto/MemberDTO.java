package com.javagroup3.javakiosk.dto;

import com.javagroup3.javakiosk.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    // Spring 에서 DTO 를 초기화 할 때 멤버 변수를 Null 로 초기화 할 수 있도록
    // Primitive type 대신 Wrapper Class 를 사용할 것.
    private Long id; // 유저 아이디

    @NotEmpty
    private String username; // 유저 이름
    @NotEmpty
    private String password; // 비밀번호
    private String email; // 이메일
    private String phone_number; // 전화번호
    private Integer age = 0; // 나이

    public static MemberDTO toDTO(Member entity){
        return MemberDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .phone_number(entity.getPhone_number())
                .age(entity.getAge())
                .build();
    }
}