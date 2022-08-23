package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.*;
import com.sparta.week3_1.entity.Comment;
import com.sparta.week3_1.security.UserDetailsImpl;
import com.sparta.week3_1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}") // 여기서 id는 article id
    public CommentResponseDtoList getComments(@PathVariable Long id) {
        List<Comment> returnData = commentService.getComments(id);
        return new CommentResponseDtoList(returnData);
    }

    @PostMapping("/auth")
    public CommentResponseDto writeComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String nickname = userDetails.getUser().getNickname();
        Comment returnData = commentService.writeComment(requestDto, nickname);
        return new CommentResponseDto(returnData);
    }

    @PutMapping("/auth/{id}") // 여기서 id는 comment id
    public CommentResponseDto updatePost(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String nickname = userDetails.getUser().getNickname();
        Comment returnData = commentService.updateComment(id, requestDto, nickname);
        return new CommentResponseDto(returnData);
    }

    @DeleteMapping("/auth/{id}") // 여기서 id는 comment id
    public CommentResponseDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String nickname = userDetails.getUser().getNickname();
        commentService.deleteComment(id, nickname);
        return new CommentResponseDto(null);
    }
}
