package com.web.exampleshinwoo.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.exampleshinwoo.domain.common.SearchCategoryStatus;
import com.web.exampleshinwoo.domain.common.SearchStatus;
import com.web.exampleshinwoo.domain.entity.Board;
import com.web.exampleshinwoo.repository.BoardRepositoryCustom;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.web.exampleshinwoo.domain.entity.QBoard.board;

@Slf4j
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    /*
    https://fordeveloper2.tistory.com/9705 정리가 너무 잘 되어있다.
    신우개발 프로젝트 뜯을때 querydsl부분이 JPAQueryFactory 사용과 new JPAQuery<>가 혼용되어있어
    처음에 혼란이 왔다. 위의 링크의 글을 보고 해결하였다.
    또한 인프런의 김영한님이 답변주신게 있었는데 이건 링크가 너무 길어서...
    결론은 둘의 성능차이는 없으나 JPAQueryFactory를 사용하여 querydsl을 구현하는게 장점이 더 많다고 하셨다.
    */
    @Override
    public List<Board> getAllBoards(SearchStatus<SearchCategoryStatus.BoardCategory> boardSearchStatus) {

        return jpaQueryFactory.select(board)
                .from(board)
                .where(
                        eqTitle(boardSearchStatus),
                        eqWriter(boardSearchStatus),
                        eqContent(boardSearchStatus))
                // querydsl은 where절에 null이 들어가면 알아서 제외시켜준다.
                .orderBy(board.createdDate.desc())
                .fetch();
    }

    @Override
    public Page<Board> getPagingBoardList(SearchStatus<SearchCategoryStatus.BoardCategory> searchStatus, Pageable pageable) {

        // 검색조건에 따른 검색 결과 쿼리
        List<Board> pagingBoardList = jpaQueryFactory.select(board)
                .from(board)
                .where(
                        eqTitle(searchStatus),
                        eqWriter(searchStatus),
                        eqContent(searchStatus)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.createdDate.desc())
                .fetch();

        // 검색조건에 따른 결과 수 카운팅.
        JPAQuery<Long> searchedResultQuery = jpaQueryFactory.select(board.count())
                .from(board)
                .where(
                        eqTitle(searchStatus),
                        eqWriter(searchStatus),
                        eqContent(searchStatus)
                );

        return PageableExecutionUtils.getPage(pagingBoardList, pageable, searchedResultQuery::fetchOne);
    }

    // todo : 검색 조건이 추가될때마다 코드를 추가할수는 없는 노릇이다. 리팩토링을 해야한다. 어떻게..?
    private BooleanExpression eqTitle(SearchStatus<SearchCategoryStatus.BoardCategory> searchStatus) {
        String keyword = searchStatus.getKeyword();
        SearchCategoryStatus.BoardCategory category = searchStatus.getCategory();

        return SearchCategoryStatus.BoardCategory.TITLE.equals(category) ? board.title.contains(keyword) : null;
    }

    private BooleanExpression eqContent(SearchStatus<SearchCategoryStatus.BoardCategory> searchStatus) {
        String keyword = searchStatus.getKeyword();
        SearchCategoryStatus.BoardCategory category = searchStatus.getCategory();

        return SearchCategoryStatus.BoardCategory.CONTENT.equals(category) ? board.content.contains(keyword) : null;
    }

    private BooleanExpression eqWriter(SearchStatus<SearchCategoryStatus.BoardCategory> searchStatus) {
        String keyword = searchStatus.getKeyword();
        SearchCategoryStatus.BoardCategory category = searchStatus.getCategory();

        return SearchCategoryStatus.BoardCategory.WRITER.equals(category) ? board.writer.contains(keyword) : null;
    }


    /*
    전체검색 로직이 돌아가지 않는다. 왜지??
    private BooleanExpression eqAll(SearchStatus<SearchCategoryStatus.BoardCategory> searchStatus) {
        String keyword = searchStatus.getKeyword();
        SearchCategoryStatus.BoardCategory category = searchStatus.getCategory();

        return SearchCategoryStatus.BoardCategory.WHOLE.equals(category) ?
                board.content.contains(keyword).and(board.title.contains(keyword)).and(board.writer.contains(keyword)) : null;
    }*/
}
