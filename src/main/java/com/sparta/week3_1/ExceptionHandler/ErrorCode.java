package com.sparta.week3_1.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@JsonFormat(shape = OBJECT) // Response에서 Enum 객체 전체 반환
@AllArgsConstructor
@Getter
public enum ErrorCode {

    //비밀번호 불일치
    WRONG_PASSWORD("WRONG_PASSWORD", "Wrong Password"),

    //NULL_ID
    NULL_ID("NULL_ID", "Id Doesn't Exist");

    private final String code;
    private final String message;
}
