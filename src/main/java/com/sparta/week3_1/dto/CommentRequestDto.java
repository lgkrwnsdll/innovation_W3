package com.sparta.week3_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CommentRequestDto {

    private Long postId;
    private String content;
}
