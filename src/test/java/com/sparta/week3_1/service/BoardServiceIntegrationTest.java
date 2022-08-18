package com.sparta.week3_1.service;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.entity.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class BoardServiceIntegrationTest {

    @Autowired BoardService boardService;

    @Test
    public void 게시글작성() {
        //given
        ArticleRequestDto requestDto = new ArticleRequestDto("title", "author", "content", 1234);

        //when
        Article article1 = boardService.writePost(requestDto); // 입력 데이터
        Long id = article1.getId();

        Article article2 = boardService.getPost(id); // 추출 데이터

        //then
        Assertions.assertThat(article2).isEqualTo(article1);
    }
}
