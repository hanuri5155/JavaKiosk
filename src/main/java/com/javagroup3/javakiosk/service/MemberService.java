package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String nickname, String password) {
        return memberRepository.findById(nickname)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
