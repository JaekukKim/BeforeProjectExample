package com.web.exampleshinwoo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
public class ExampleshinwooApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleshinwooApplication.class, args);
        log.info("코드 스타일 맞춰보기 프로젝트 실행.");
    }

}
