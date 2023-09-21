package com.web.exampleshinwoo.controller;

import com.web.exampleshinwoo.domain.common.SearchCategoryStatus;
import com.web.exampleshinwoo.domain.common.SearchStatus;
import com.web.exampleshinwoo.domain.entity.Board;
import com.web.exampleshinwoo.model.request.BoardRequest;
import com.web.exampleshinwoo.model.response.BoardResponse;
import com.web.exampleshinwoo.model.response.Response;
import com.web.exampleshinwoo.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Tag(name = "프로젝트 투입전 연습용", description = "현 진행중인 프로젝트의 코드스타일 연습하기")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "리스트 불러오기",description = "페이징 기능 없음")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "실패")
    })
    @GetMapping("/getboardlist")
    public Response<List<BoardResponse.BoardResponseDto>> getBoardList(
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = "TITLE", required = false) SearchCategoryStatus.BoardCategory category
            // 스프링은 기본적으로 StringToEnumConverter가 존재. 하지만 enum특성상 대문자로 받아주어야함
            // 대문자를 사용하기 원치 않는다면 CustomConverter를 만들어주어야 한다.
            ) {
        log.info("키워드에 따른 리스트 검색 시작 : {} , {}", category.toString(), keyword);

        return Response.<List<BoardResponse.BoardResponseDto>>builder()
                .data(boardService.getBoardList(new SearchStatus<SearchCategoryStatus.BoardCategory>(keyword, category)))
                .build();
    }

    @Operation(summary = "리스트 불러오기",description = "페이징 기능 있음")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "실패")
    })
    @GetMapping("/getpagingboardlist")
    public Response<BoardResponse.BoardPageIngredient> getPagingBoardList(
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = "TITLE", required = false) SearchCategoryStatus.BoardCategory category,
            @PageableDefault(size = 7) Pageable pageable
    ){
        log.info("페이징 적용된 게시글 리스트 불러오기, 검색 키워드와 카테고리 : {} , {}", keyword,category);

        return Response.<BoardResponse.BoardPageIngredient>builder()
                .data(
                        boardService.getPagingBoardList(
                                new SearchStatus<SearchCategoryStatus.BoardCategory>(keyword, category), pageable)
                )
                .build();
    }

    @Operation(summary = "게시글 등록",description = "게시글 등록 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "실패")
    })
    @PostMapping("/registboard")
    public Response<BoardResponse.BoardResponseDto> insertIntoBoard(@RequestBody BoardRequest.InsertIntoBoard boardRequest) {
        log.info("API 게시글 작성 : {}", boardRequest.toString());

        Board board = boardService.insertIntoBoard(boardRequest);

        return Response.<BoardResponse.BoardResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .statusMessage(HttpStatus.CREATED.getReasonPhrase())
                // 응답이 성공하는지만 테스트. 실제 서비스에선 void로 맞추고 data 안넘겨도 상관없을듯.
                .data(board.toDTO())
                .build();
    }
}
