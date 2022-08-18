package com.sparta.week3_1.dto;

import com.sparta.week3_1.ExceptionHandler.ErrorCode;
import com.sparta.week3_1.entity.Article;
import lombok.Getter;

@Getter
public class ExceptionDto {
    private final boolean success = false;
    private final Article data = null;
    private final ErrorCode error;

    public ExceptionDto(ErrorCode error) {
        this.error = error;
    }
}
