package com.sparta.week3_1.service;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.repository.BoardRepository;
import com.sparta.week3_1.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Article update(Long id, ArticleRequestDto requestDto) {
        Article article1 = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        article1.update(requestDto);
        return article1;
    }

    @Transactional
    public Article findById(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }
}
