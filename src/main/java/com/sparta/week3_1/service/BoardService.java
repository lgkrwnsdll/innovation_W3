package com.sparta.week3_1.service;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.repository.BoardRepository;
import com.sparta.week3_1.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public List<Article> getAllPosts() {
        return boardRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public Article getPost(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }

    @Transactional
    public Article writePost(ArticleRequestDto requestDto) {
        Article article = new Article(requestDto); // 받아온 dto로 엔티티 생성해 db에 저장
        return boardRepository.save(article);
    }

    @Transactional
    public Article updatePost(Long id, ArticleRequestDto requestDto) {
        Article article = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        article.update(requestDto);
        return article;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
