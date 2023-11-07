package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.dto.MemberDTO;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public MemberDTO login(MemberDTO memberDTO){
        /*
        1. 회원이 입력한 ID(nickname)로 DB에서 조회
        2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        */
        Optional<Member> byMemberNickname = memberRepository.findByNickname(memberDTO.getNickname());
        if (byMemberNickname.isPresent()){
            // 해당 유저 닉네임을 가진 회원 정보가 있다면
            Member memberEntity = byMemberNickname.get();
            if(memberEntity.getPassword().equals(memberDTO.getPassword())){
                // 비밀번호가 일치
                MemberDTO dto = MemberDTO.toDTO(memberEntity);
                System.out.println("비밀번호 일치");
                return dto;
            }else {
                // 비밀번호 불일치(로그인 실패)
                System.out.println("비밀번호 불일치");
                return null;
            }
        }else {
            // 해당 유저 닉네임을 가진 회원 정보가 없다면
            return null; // 로그인 실패
        }
    }
}
