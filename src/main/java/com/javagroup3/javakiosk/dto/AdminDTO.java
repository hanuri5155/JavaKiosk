package com.javagroup3.javakiosk.dto;

import com.javagroup3.javakiosk.entity.Admin;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDTO {
    @NotEmpty
    private String adminNickname; // 관리자 닉네임
    @NotEmpty
    private String adminUsername; // 관리자 이름
    @NotEmpty
    private String adminPassword; // 관리자 비밀번호

    public static AdminDTO toDTO(Admin entity){
        return AdminDTO.builder()
                .adminNickname(entity.getAdminNickname())
                .adminUsername(entity.getAdminUsername())
                .adminPassword(entity.getAdminPassword())
                .build();
    }
}
