package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.dto.ArticleResponseDto;
import com.sparta.week3_1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller + @Responsebody
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class BoardController {
    private final BoardService boardService;


    @GetMapping()
    public ArticleResponseDto.Multi getAllPosts() {
        return new ArticleResponseDto.Multi(true, boardService.getAllPosts(), null);
    }

    @GetMapping("/{id}")
    public ArticleResponseDto.Single getPost(@PathVariable Long id) {
        return new ArticleResponseDto.Single(true, boardService.getPost(id), null);
    }

    @PostMapping()
    public ArticleResponseDto.Single writePost(@RequestBody ArticleRequestDto requestDto) {
        return new ArticleResponseDto.Single(true, boardService.writePost(requestDto), null);
    }

    @PostMapping("/{id}")
    public ArticleResponseDto.Single checkPw(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        if (requestDto.getPassword() != boardService.getPost(id).getPassword()) { // 불일치
            return new ArticleResponseDto.Single(false, null, "WRONG_PW");
        } else { // 일치
            return new ArticleResponseDto.Single(true, null, null);
        }
    }

    @PutMapping("/{id}")
    public ArticleResponseDto.Single updatePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return new ArticleResponseDto.Single(true, boardService.updatePost(id, requestDto), null);
    }

    @DeleteMapping("/{id}")
    public ArticleResponseDto.Single deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
        return new ArticleResponseDto.Single(true, null, null);
    }
}