package com.web.exampleshinwoo.service;

import com.web.exampleshinwoo.domain.common.SearchCategoryStatus;
import com.web.exampleshinwoo.domain.common.SearchStatus;
import com.web.exampleshinwoo.domain.entity.Board;
import com.web.exampleshinwoo.model.request.BoardRequest;
import com.web.exampleshinwoo.model.resources.PageIngredient;
import com.web.exampleshinwoo.model.response.BoardResponse;
import com.web.exampleshinwoo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    // todo : 주석처리 연구일지 쓰고 지우기

    private final BoardRepository boardRepository;

    public List<BoardResponse.BoardResponseDto> getBoardList(
            SearchStatus<SearchCategoryStatus.BoardCategory> boardSearchStatus) {

        List<Board> board = boardRepository.getAllBoards(boardSearchStatus);

        if (board.isEmpty()) {

            throw new IllegalArgumentException("검색결과가 존재하지 않습니다.");
        }

        return BoardResponse.BoardResponseDto.from(board);
        /*

        board.stream() : board 컬렉션을 stream으로 변환
        .map(BoardResponse.BoardResponseDto::new) : map 연산자를 활용하여 board의 각 요소를
        BoardResponse.BoardResponseDto 객체로 변환. ::new 는 관련 생성자를 참조하여 객체를 생성하겠다는 의미.
        .collect() : map 연산 결과를 stream에서 () 내부에 선언된 콜렉션 형태로 변경.
        Collectors.toList() : List로 변경.

        09.20 정적 팩토리 메소드를 활용하여 리팩토링.
        */
        // 검색 결과에 따른 반환여부는 service단에서 결정지어주어야 한다.
        /*board.stream().map(BoardResponse.BoardResponseDto::of).toList()*/
    }

    // 기본적인 CRUD는 JPA에서 빵빵하게 지원해주기에 querydsl을 굳이??
    public Board insertIntoBoard(BoardRequest.InsertIntoBoard boardRequest) {
        Board board = boardRequest.toEntity();

        return boardRepository.save(board);
    }

    public BoardResponse.BoardPageIngredient getPagingBoardList(SearchStatus<SearchCategoryStatus.BoardCategory> boardSearchStatus, Pageable pageable) {

        Page<Board> pagingBoardList = boardRepository.getPagingBoardList(boardSearchStatus, pageable);

        List<BoardResponse.BoardResponseDto> boardPageIngredients = pagingBoardList.map(BoardResponse.BoardResponseDto::from).getContent();

        /*long totalElements = pagingBoardList.getTotalElements();*/

        return BoardResponse.BoardPageIngredient.of(PageIngredient.
                <BoardResponse.BoardResponseDto>builder()
                .pagedList(boardPageIngredients)
                .totalPages(pagingBoardList.getTotalPages())
                .totalElements(pagingBoardList.getTotalElements())
                .currentPage(pagingBoardList.getNumber())
                .build());
    }
}
