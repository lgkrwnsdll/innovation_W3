package com.sparta.week3_1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public class ArticleRequestDto { // 테이블의 데이터에 접근할 때의 완충재

    private String title;

    private String content;

    private String author;

    private int password;

    public ArticleRequestDto(String title, String content, String author, int password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }
}