package com.web.exampleshinwoo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @Size(max = 20)
    @Column(name = "user_id", nullable = false, unique = true)
    String username;

    @Size(min = 8 , max = 511)
    @Column(name = "password", nullable = false)
    String password;

    /*
    시큐리티에서 사용하는거 일단 잠굼
    @Column(name = "user_role")
    Role role;
    */

    @Builder
    public Member(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
