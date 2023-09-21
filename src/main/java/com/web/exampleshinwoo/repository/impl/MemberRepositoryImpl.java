package com.web.exampleshinwoo.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.exampleshinwoo.domain.entity.Member;
import com.web.exampleshinwoo.repository.MemberRepositoryCustom;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.web.exampleshinwoo.domain.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> getAllMembers() {
        return jpaQueryFactory.select(member)
                .from(member)
                .fetch();
    }
}
