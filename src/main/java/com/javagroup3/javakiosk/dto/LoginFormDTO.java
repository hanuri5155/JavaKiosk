package com.javagroup3.javakiosk.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginFormDTO {
    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;
}
