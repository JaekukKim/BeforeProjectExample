package com.web.exampleshinwoo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.exampleshinwoo.domain.entity.Board;
import com.web.exampleshinwoo.domain.entity.QBoard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import(QueryDslConfig.class) // 컨픽을 따로 해주었기에 임포트해서 진행.
@Transactional
class QueryDslConfigTest {
    @PersistenceContext
    EntityManager entityManager;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach // 테스트 메소드 실행 이전에 한번만 실행됨
    @Transactional
    void initTest(){
        jpaQueryFactory = new JPAQueryFactory(entityManager);

        Board board1 = Board.builder()
                .title("제목1")
                .content("내용1")
                .writer("작성자1")
                .build();
        Board board2 = Board.builder()
                .title("제목2")
                .content("내용2")
                .writer("작성자2")
                .build();
        Board board3 = Board.builder()
                .title("제목3")
                .content("내용3")
                .writer("작성자3")
                .build();
        Board board4 = Board.builder()
                .title("제목4")
                .content("내용4")
                .writer("작성자4")
                .build();
        entityManager.persist(board1);
        entityManager.persist(board2);
        entityManager.persist(board3);
        entityManager.persist(board4);
    }

    @Test
    @DisplayName("queryDSL insert & select 테스트")
    void queryDslTest(){
        QBoard board = QBoard.board;

        List<Board> result1 = jpaQueryFactory.select(board)
                .from(board)
                .where(board.writer.eq("작성자1"))
                .fetch();
        // fetch()로 받을땐 데이터가 여러건일 수 있다. => List
        // List로 받는게 아니다? => fetchOne()

        Board result2 = jpaQueryFactory.selectFrom(board)
                .where(board.title.eq("제목2"))
                .fetchOne();

        List<Board> result3 = jpaQueryFactory.select(board)
                .from(board)
                .where(board.content.contains("용"))
                .fetch();

        Assertions.assertThat(result1.get(0).getWriter()).isEqualTo("작성자1");
        Assertions.assertThat(result2.getWriter()).isEqualTo("작성자2");
        Assertions.assertThat(result1.get(0).getWriter()).isEqualTo("작성자1");
    }
}