package com.javagroup3.javakiosk.config;

import com.javagroup3.javakiosk.entity.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// Spring Security 의 UserDetails 인터페이스를 구현한 User 클래스를 상속하는 클래스입니다.
// 이 클래스를 만든 이유는 기존의 User 클래스는 id 값을 참조할 수 없기 때문입니다.
// 이렇게 되면 OrderRecord 에 새로운 행을 삽입할 때 Principal 이나 Authentication 객체로부터
// 멤버의 id 값을 참조할 수 없기 때문에 아래와 같이 id 필드를 추가한 CurrentMember 클래스를 정의합니다.
@Getter
@ToString
public class CurrentMember extends User {
    private Long id;
    private String username;
    public CurrentMember(String username, String password, Collection<? extends GrantedAuthority> authorities, Member member) {
        super(username, password, authorities);
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
