package com.web.exampleshinwoo.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SearchCategoryStatus {

    @Getter
    @AllArgsConstructor
    public enum BoardCategory {
        TITLE("title", "제목"),
        CONTENT("content", "내용"),
        WRITER("writer", "글쓴이"),
        /*WHOLE("whole", "전체") => 잠시 막아둠 */;

        private final String key;
        private final String value;

        // 들어온 데이터 확인용
        @Override
        public String toString() {
            return "Category{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}