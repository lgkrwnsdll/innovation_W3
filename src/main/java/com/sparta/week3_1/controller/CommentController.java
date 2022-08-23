package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.CommentRequestDto;
import com.sparta.week3_1.dto.CommentResponseDto;
import com.sparta.week3_1.dto.CommentResponseDtoList;
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

    @GetMapping("/{id}")
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
}
