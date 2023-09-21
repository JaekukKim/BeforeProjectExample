package com.web.exampleshinwoo.model.request;

import com.web.exampleshinwoo.domain.entity.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberRequest {

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class signUpMember {

        @NotNull
        @Size(max = 20)
        String username;

        @NotNull
        @Size(min = 8 , max = 511)
        String password;

        public Member toEntity(){
            return Member.builder()
                    .username(username)
                    .password(password)
                    .build();
        }
    }
}
