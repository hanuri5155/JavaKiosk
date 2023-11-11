package com.javagroup3.javakiosk.authority;

import lombok.Getter;

@Getter
public enum MemberAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    private String name;

    private MemberAuthority(String name) {
        this.name = name;
    }
}