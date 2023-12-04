package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// 회원과 관련된 로직을 작성하는 클래스입니다.
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 새로운 유저를 DB 에 생성하는 코드입니다.
    // Spring Security 에서 제공하는 passwordEncoder로 패스워드를 암호화하여 저장합니다.

    public Member create(
            String username, String password, String email, String phoneNumber,
            int age
    ){
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setEmail(email);
        member.setPhone_number(phoneNumber);
        member.setAge(age);

        this.memberRepository.save(member);

        return member;
    }

    public List<MemberDTO> findAll() {
        List<Member> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member memberEntity: memberEntityList) {
            memberDTOList.add(MemberDTO.toDTO(memberEntity));
            // 아래랑 같은 의미
            //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            //memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }
}