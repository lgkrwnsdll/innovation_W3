package com.sparta.week3_1.service;

import com.sparta.week3_1.ExceptionHandler.CustomException;
import com.sparta.week3_1.dto.CommentRequestDto;
import com.sparta.week3_1.entity.Article;
import com.sparta.week3_1.entity.Comment;
import com.sparta.week3_1.repository.BoardRepository;
import com.sparta.week3_1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.sparta.week3_1.ExceptionHandler.ErrorCode.NOT_ALLOWED;

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

    @Transactional
    public Comment updateComment(Long id, CommentRequestDto requestDto, String author) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        if (author.equals(comment.getAuthor())) {
            comment.update(requestDto);
        } else {
            throw new CustomException(NOT_ALLOWED);
        }
        return comment;
    }

    public void deleteComment(Long id, String author) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        if (author.equals(comment.getAuthor())) {
            commentRepository.deleteById(id);
        } else {
            throw new CustomException(NOT_ALLOWED);
        }
    }
}
