package com.sparta.week3_1.service;

import com.sparta.week3_1.ExceptionHandler.CustomException;
import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.repository.BoardRepository;
import com.sparta.week3_1.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.sparta.week3_1.ExceptionHandler.ErrorCode.NO_AUTHORITY;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

//    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌 // 연산이 고립되어 다른 연산과의 혼선 방지, 연산이 도중에 실패할 경우 변경사항 커밋되지 않음
    public List<Article> getAllPosts() {
        return boardRepository.findAllByOrderByIdDesc();
    }

    public Article getPost(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public Article writePost(ArticleRequestDto requestDto, String author) {
        Article article = new Article(requestDto, author); // 받아온 dto로 엔티티 생성해 db에 저장
        return boardRepository.save(article);
    }

    //public void checkPw(Long id, ArticleRequestDto requestDto) {
    //    if (requestDto.getPassword() != getPost(id).getPassword()) {
    //        throw new CustomException(WRONG_PASSWORD);
    //    }
    //}

    @Transactional // update에는 트랜잭션 필수
    public Article updatePost(Long id, ArticleRequestDto requestDto) {
        Article article = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        article.update(requestDto);
        return article;
    }

    public void deletePost(Long id, String author) {
        Article article = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        if (author.equals(article.getAuthor())) {
            boardRepository.deleteById(id);
        } else {
            throw new CustomException(NO_AUTHORITY);
        }
    }
}
