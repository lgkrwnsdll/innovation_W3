package com.sparta.week3_1.service;

import com.sparta.week3_1.dto.CommentRequestDto;
import com.sparta.week3_1.entity.Article;
import com.sparta.week3_1.entity.Comment;
import com.sparta.week3_1.repository.BoardRepository;
import com.sparta.week3_1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public List<Comment> getComments(Long id) {
        return commentRepository.findAllByArticleId(id);
    }

    public Comment writeComment(CommentRequestDto requestDto, String nickname) {
        Long postId = requestDto.getPostId();
        Article article = boardRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        Comment comment = new Comment(requestDto, article, nickname);
        return commentRepository.save(comment);
    }
}
