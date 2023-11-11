package com.javagroup3.javakiosk.repository;

import com.javagroup3.javakiosk.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, String> {
    // nickname으로 회원 정보 조회, select * from member_table where nickname=? 이과 같은 쿼리문 수행
    Optional<Member> findByUsername(String username);
}
