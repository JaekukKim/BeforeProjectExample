package com.web.exampleshinwoo.model.resources;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageIngredient<T> {

    int currentPage;

    int totalPages;

    long totalElements;

    List<T> pagedList;

    // todo : 정적팩토리 메소드는 일단 미뤄둠. pagedList부분을 어떻게 받아야할지 모르겠음.
    /*public static PageIngredient<?> of(PageIngredient<?> list){
        return PageIngredient.builder()
                .currentPage(list.getCurrentPage())
                .totalPages(list.getTotalPages())
                .totalElements(list.getTotalElements())
                .pagedList(Collections.singletonList(list.getPagedList()))
                .build();
    }*/
}
