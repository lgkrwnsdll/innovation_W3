package com.sparta.week3_1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 프로젝트 전역에서 발생하는 에러 관리
@RequiredArgsConstructor
public class ExceptionController {

    private final ResponseService responseService;

    // 아래의 에러 발생 시 해당 메소드 실행하여 응답 반환

    @ExceptionHandler(value = IllegalArgumentException.class)
    private CommonResponse illegalArgumentException(IllegalArgumentException e) {
        log.info(e.getMessage());
        return responseService.getNullIdResponse();
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    private CommonResponse emptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.info(e.getMessage());
        return responseService.getNullIdResponse();
    }

}
