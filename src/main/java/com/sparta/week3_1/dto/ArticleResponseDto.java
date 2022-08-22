package com.sparta.week3_1.dto;

import com.sparta.week3_1.ExceptionHandler.ErrorCode;
import com.sparta.week3_1.entity.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {
    private final boolean success = true;
    private final Article data;
    private final ErrorCode error = null;

    public ArticleResponseDto(Article data) {
        this.data = data;
    }
}
