package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.dto.AdminDTO;
import com.javagroup3.javakiosk.entity.Admin;
import com.javagroup3.javakiosk.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    /**
     * @return null 로그인 실패
     */
    public AdminDTO login(AdminDTO adminDTO){
        /*
        1. 관리자가 입력한 ID(adminNickname)로 DB에서 조회
        2. DB에서 조회한 비밀번호와 관리자가 입력한 비밀번호가 일치하는지 판단
        */
        Optional<Admin> byAdminNickname = adminRepository.findByAdminNickname(adminDTO.getAdminNickname());
        if (byAdminNickname.isPresent()){
            // 해당 관리자 닉네임을 가진 관리자 정보가 있다면
            Admin adminEntity = byAdminNickname.get();
            if(adminEntity.getAdminPassword().equals(adminDTO.getAdminPassword())){
                // 비밀번호가 일치
                AdminDTO dto = AdminDTO.toDTO(adminEntity);
                System.out.println("관리자 비밀번호 일치");
                return dto;
            }else {
                // 비밀번호 불일치(로그인 실패)
                System.out.println("관리자 비밀번호 불일치");
                return null;
            }
        }else {
            // 해당 유저 닉네임을 가진 회원 정보가 없다면
            return null; // 로그인 실패
        }
    }
}