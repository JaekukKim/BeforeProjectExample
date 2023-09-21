package com.web.exampleshinwoo.domain.entity;

import lombok.Getter;
import lombok.ToString;

// 여기 안씀, 시큐리티 해볼려고
@Getter
@ToString
public enum Role {
    ROLE_ADMIN("관리자"),
    ROLE_MEMBER("일반회원");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
