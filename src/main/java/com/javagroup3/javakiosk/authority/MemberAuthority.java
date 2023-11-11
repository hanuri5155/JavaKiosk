package com.javagroup3.javakiosk.authority;

import lombok.Getter;

@Getter
public enum MemberAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String name;

    private MemberAuthority(String name) {
        this.name = name;
    }
}