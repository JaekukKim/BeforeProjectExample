package com.web.exampleshinwoo.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.exampleshinwoo.domain.entity.Member;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MemberResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class MemberResponseDto {

        Long id;

        String username;

        @JsonIgnore
        String password;

        @Builder
        public MemberResponseDto(Long id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }

        // 정적 팩토리 메소드
        /*
        사용 이유가 몰까??

        */
        public static List<MemberResponseDto> from(List<Member> memberList) {
            return memberList.stream().map(member -> MemberResponseDto.builder()
                    .id(member.getId())
                    .password(member.getPassword())
                    .username(member.getUsername())
                    .build()
            ).toList();
        }

    }
}
