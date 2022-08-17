package com.sparta.week3_1.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //비밀번호 불일치
    WRONG_PASSWORD("WRONG_PASSWORD", "WRONG_PASSWORD");

    private final String code;
    private final String message;
}
