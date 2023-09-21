package com.web.exampleshinwoo.repository;

import com.web.exampleshinwoo.domain.common.SearchCategoryStatus;
import com.web.exampleshinwoo.domain.common.SearchStatus;
import com.web.exampleshinwoo.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    // todo : querydsl 연습용으로 jpa 기본 crud를 사용하지 않고 querydsl만으로 구현해보기.

    List<Board> getAllBoards(SearchStatus<SearchCategoryStatus.BoardCategory> boardSearchStatus);

    Page<Board> getPagingBoardList(SearchStatus<SearchCategoryStatus.BoardCategory> boardSearchStatus, Pageable pageable);

}
