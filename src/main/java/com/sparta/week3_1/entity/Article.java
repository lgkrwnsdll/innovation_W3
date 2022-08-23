package com.sparta.week3_1.entity;

import com.sparta.week3_1.dto.ArticleRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성, 접근 권한 protected 지정
@Entity // DB 테이블 역할
@Getter
public class Article extends Timestamped {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;
    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;
    @Column(nullable = false)
    private String content;

    //@Column(nullable = false)
    //private String author;
    //@Column(nullable = false)
    //@JsonIgnore // 응답에 해당 데이터 포함하지 않음
    //private int password;

    @Column(nullable = false)
    private String author;


    public Article(ArticleRequestDto requestDto, String author) {
        this.author = author;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        //this.author = requestDto.getAuthor();
        //this.password = requestDto.getPassword();
    }

    public void update(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        //this.author = requestDto.getAuthor();
        //this.password = requestDto.getPassword();
    }

}
