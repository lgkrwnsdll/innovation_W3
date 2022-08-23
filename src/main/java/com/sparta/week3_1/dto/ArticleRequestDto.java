package com.sparta.week3_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ArticleRequestDto { // 테이블의 데이터에 접근할 때의 완충재

    private String title;

    private String content;

    //private String author;
    //
    //private int password;

}