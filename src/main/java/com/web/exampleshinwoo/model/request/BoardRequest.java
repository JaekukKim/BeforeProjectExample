package com.web.exampleshinwoo.model.request;

import com.web.exampleshinwoo.domain.entity.Board;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@UtilityClass
public class BoardRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class InsertIntoBoard{

        Long id;

        @NotNull
        @Size(min = 2, max = 255)
        String title;

        @NotNull
        String content;

        @Size(max = 20)
        @NotNull
        String writer;

        @CreatedDate
        private LocalDateTime createdDate;

        @LastModifiedDate
        private LocalDateTime lastModifiedDate;

        public Board toEntity(){
            return Board.builder()
                    .writer(writer)
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}
