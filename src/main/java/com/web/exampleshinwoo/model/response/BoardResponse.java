package com.web.exampleshinwoo.model.response;

import com.web.exampleshinwoo.domain.entity.Board;
import com.web.exampleshinwoo.model.resources.PageIngredient;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class BoardResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class BoardResponseDto {

        private Long id;

        private String title;

        private String content;

        private String writer;

        private LocalDateTime createdDate;

        private LocalDateTime lastModifiedDate;

        // 정적 팩토리 메소드 (개념모름 일단 구현해보자! -> 실습 후 개념주입 꼮 하기)
        /*
        파라미터의 갯수로 네이밍 규칙이 존재함.
        1개 : from
        여러개 : of
        */
        public static List<BoardResponseDto> from(List<Board> boardList){
            return boardList.stream().map(board -> BoardResponseDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build()
            ).toList();
        }
        // 페이지 결과 정적 팩토리 메소드
        public static Page<BoardResponseDto> from(Page<Board> boardList){
            return boardList.map(board -> BoardResponseDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build()
            );
        }

        public static BoardResponseDto from(Board board) {
            return BoardResponseDto.builder()
                    .id(board.getId())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .title(board.getTitle())
                    .createdDate(board.getCreatedDate())
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class BoardPageIngredient {

        /*int page;

        long totalElements;

        List<BoardResponseDto> boardList;*/

        /*내 나름대로 효율적인 코드와 재사용성을 고려하여 페이징에 필요한 요소들을 특정 클래스에 선언하여 정리한 뒤 페이징이 필요한
        객체별로 불러다가 쓸 수 있도록 작성 해 보았다.*/
        PageIngredient<BoardResponseDto> boardList;

        /*public static BoardPageIngredient of(long totalElements, List<BoardResponseDto> boardList) {

            return BoardPageIngredient.builder()
                    .totalElements(totalElements)
                    .boardList(boardList)
                    .build();
        }*/

        public static BoardPageIngredient of(PageIngredient<BoardResponseDto> boardList){
            return BoardPageIngredient.builder()
                    .boardList(boardList)
                    .build();
        }

    }
}
