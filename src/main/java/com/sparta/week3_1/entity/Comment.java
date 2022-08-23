package com.sparta.week3_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week3_1.dto.CommentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성, 접근 권한 protected 지정
@Entity // DB 테이블 역할
@Getter
public class Comment extends Timestamped {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    @JsonIgnore
    private Article article;

    public Comment(CommentRequestDto requestDto, Article article, String author) {
        this.author = author;
        this.article = article;
        //this.title = requestDto.getPostId();
        this.content = requestDto.getContent();
        //this.author = requestDto.getAuthor();
        //this.password = requestDto.getPassword();
    }

    public void update(CommentRequestDto requestDto) {
        //this.title = requestDto.getPostId();
        this.content = requestDto.getContent();
        //this.author = requestDto.getAuthor();
        //this.password = requestDto.getPassword();
    }

}
