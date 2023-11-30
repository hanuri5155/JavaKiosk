package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.authority.MemberAuthority;
import com.javagroup3.javakiosk.config.CurrentMember;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

// 스프링 시큐리티에서 제공하는 UserDetailsService 인터페이스를 구현하는 클래스입니다.
// memberRepository 를 통해 DB 에 접근하여 유저를 찾고 권한을 설정한 다음, 멤버 정보를 얻을 수 있도록 CurrentMember 인스턴스를 반환합니다.
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> _members = this.memberRepository.findByUsername(username);
        if (_members.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Member member = _members.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(MemberAuthority.ADMIN.getName()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberAuthority.USER.getName()));
        }
        return new CurrentMember(member.getUsername(), member.getPassword(), authorities, member);
    }
}