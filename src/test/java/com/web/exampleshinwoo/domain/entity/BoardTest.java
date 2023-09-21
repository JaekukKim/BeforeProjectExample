package com.web.exampleshinwoo.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("게시글 등록 테스트")
@Transactional
class BoardTest {

    @Test
    void insertIntoBoard(){

    }
}