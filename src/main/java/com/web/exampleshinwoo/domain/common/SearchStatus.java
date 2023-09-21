package com.web.exampleshinwoo.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchStatus<T> {

    String keyword;
    T category;

    public SearchStatus(String keyword, T category) {
        this.keyword = keyword;
        this.category = category;
    }
}
