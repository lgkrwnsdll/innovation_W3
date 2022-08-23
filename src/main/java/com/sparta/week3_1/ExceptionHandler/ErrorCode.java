package com.sparta.week3_1.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@JsonFormat(shape = OBJECT) // Response에서 Enum 객체 전체 반환
@AllArgsConstructor
@Getter
public enum ErrorCode {

    //아이디, 비밀번호 형식 오류
    WRONG_FORM("WRONG_FORM", "Wrong Form"),

    //아이디 중복
    DUPLICATE_ID("DUPLICATE_ID", "Duplicate ID"),

    //비밀번호 불일치
    WRONG_PASSWORD("WRONG_PASSWORD", "Wrong Password"),

    //NULL_ID
    NULL_ID("NULL_ID", "Id Doesn't Exist"),

    //권한 없음
    NO_AUTHORITY("NO_AUTHORITY", "Unauthorized Or No Token");

    private final String code;
    private final String message;
}
