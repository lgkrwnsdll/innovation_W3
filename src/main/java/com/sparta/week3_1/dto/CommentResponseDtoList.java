package com.sparta.week3_1.dto;

import com.sparta.week3_1.ExceptionHandler.ErrorCode;
import com.sparta.week3_1.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentResponseDtoList {
    private final boolean success = true;
    private final List<Comment> data;
    private final ErrorCode error = null;

    public CommentResponseDtoList(List<Comment> data) {
        this.data = data;
    }
}
