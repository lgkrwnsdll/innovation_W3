package com.sparta.week3_1.entity;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.entity.Timestamped;
import com.sparta.week3_1.repository.BoardRepository;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본 생성자 생성
@Entity
public class Article extends Timestamped {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;
    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private int password;


    public Article(String title, String content, String author, int password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    public Article(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }

    public void update(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
