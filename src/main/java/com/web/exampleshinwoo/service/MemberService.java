package com.web.exampleshinwoo.service;

import com.web.exampleshinwoo.domain.entity.Member;
import com.web.exampleshinwoo.model.request.MemberRequest;
import com.web.exampleshinwoo.model.response.MemberResponse;
import com.web.exampleshinwoo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public List<MemberResponse.MemberResponseDto> getMembers(){

        List<Member> memberList = memberRepository.getAllMembers();

        return MemberResponse.MemberResponseDto.from(memberList);
    }

    @Transactional
    public void signUp(MemberRequest.signUpMember memberRequest){
        memberRepository.findByUsername(memberRequest.getUsername())
                .ifPresent(member -> {
                    throw new IllegalStateException("이미 존재하는 회원 아이디입니다.");
                }
        );
        memberRepository.save(memberRequest.toEntity()); // entity 타입 넣어줘야함.
    }
}
