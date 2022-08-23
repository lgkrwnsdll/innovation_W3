package com.sparta.week3_1.dto;

import com.sparta.week3_1.ExceptionHandler.ErrorCode;
import com.sparta.week3_1.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final boolean success = true;
    private final Comment data;
    private final ErrorCode error = null;

    public CommentResponseDto(Comment data) {
        this.data = data;
    }
}
