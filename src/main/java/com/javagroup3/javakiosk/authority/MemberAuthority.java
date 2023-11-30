package com.javagroup3.javakiosk.authority;

import lombok.Getter;

// 스프링 시큐리티의 권한 역할을 표현하는 클래스입니다.
@Getter
public enum MemberAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String name;

    private MemberAuthority(String name) {
        this.name = name;
    }
}