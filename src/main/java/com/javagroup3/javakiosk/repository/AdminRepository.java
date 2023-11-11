package com.javagroup3.javakiosk.repository;

import com.javagroup3.javakiosk.entity.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, String> {
    // admin_nickname으로 관리자 조회, select * from admin_table where admin_nickname=? 이과 같은 쿼리문 수행
    Optional<Admin> findByAdminNickname(String adminNickname);
}
