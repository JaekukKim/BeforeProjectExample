package com.web.exampleshinwoo.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response<T> {

    @Builder.Default
    int code = HttpStatus.OK.value();
    /*builder.default를 사용하기 위해선 필드가 특정 값으로 초기화 되어있어야 함.*/
    @Builder.Default
    String statusMessage = HttpStatus.OK.getReasonPhrase();

    T data;
}
