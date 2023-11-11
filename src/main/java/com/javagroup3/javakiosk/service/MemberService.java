package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
