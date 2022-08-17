package com.sparta.week3_1.dto;

import com.sparta.week3_1.ExceptionHandler.ErrorCode;
import com.sparta.week3_1.entity.Article;
import lombok.Getter;

import java.util.List;

@Getter
public class DatasResponseDto {
    private final boolean success = true;
    private final List<Article> data;
    private final ErrorCode error = null;

    public DatasResponseDto(List<Article> data) {
        this.data = data;
    }
}
