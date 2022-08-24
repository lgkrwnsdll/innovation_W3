package com.sparta.week3_1.ExceptionHandler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.sparta.week3_1.dto.ExceptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import static com.sparta.week3_1.ExceptionHandler.ErrorCode.INVALID_TOKEN;
import static com.sparta.week3_1.ExceptionHandler.ErrorCode.NULL_ID;

@Slf4j
@RestControllerAdvice // 여러 컨트롤러에 대해 전역적인 에러 관리 (AOP)
@RequiredArgsConstructor
public class CustomExceptionHandler {

    // 아래의 에러 발생 시 해당 메소드 실행하여 응답 반환

    @ExceptionHandler({IllegalArgumentException.class, EmptyResultDataAccessException.class})
    private ExceptionDto handleNullIdException (Exception e) {
        log.info(e.getMessage());
        return new ExceptionDto(NULL_ID);
    }

    // 커스텀 예외 관련 핸들링 (추가할 시 ErrorCode Enum에 등록)
    @ExceptionHandler(CustomException.class)
    private ExceptionDto handleCustomException (CustomException e) {
        log.info(String.valueOf(e.getErrorCode()));
        return new ExceptionDto(e.getErrorCode());
    }

}
