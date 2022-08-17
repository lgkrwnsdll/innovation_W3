package com.sparta.week3_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionDto {
    private final String code;
    private final String message;
}
