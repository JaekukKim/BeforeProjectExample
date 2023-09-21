package com.web.exampleshinwoo.domain.entity;

import com.web.exampleshinwoo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("멤버 인서트 테스트")
@Transactional
class MemberTest {
    // 멤버 생성 테스트
    @Autowired
    MemberRepository memberRepository;

    @Test
    void insertMember() {
        /*given*/
        Member testMember1 = Member.builder()
                .username("테스트username1")
                .password("test11234")
                .build();
        Member testMember2 = Member.builder()
                .username("테스트username2")
                .password("test21234")
                .build();
        /*when*/
        Member result1 = memberRepository.save(testMember1);
        Member result2 = memberRepository.save(testMember2);
        /*then*/
        Assertions.assertThat(testMember1.getUsername()).isEqualTo(result1.getUsername());
        Assertions.assertThat(testMember2.getUsername()).isEqualTo(result2.getUsername());
    }

}