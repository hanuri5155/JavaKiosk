package com.javagroup3.javakiosk.repository;

import com.javagroup3.javakiosk.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, String> {

}
