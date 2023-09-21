package com.web.exampleshinwoo.repository;

import com.web.exampleshinwoo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository에는 @Repository가 내장되어 있어 굳이 어노테이션 선언 안해도됨.
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByUsername(String username);
}
