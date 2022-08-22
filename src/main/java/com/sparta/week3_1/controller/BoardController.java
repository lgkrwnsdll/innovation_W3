package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.dto.ArticleResponseDto;
import com.sparta.week3_1.dto.ArticleResponseDtoList;
import com.sparta.week3_1.entity.Article;
import com.sparta.week3_1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class BoardController {
    private final BoardService boardService;

    @GetMapping()
    public ArticleResponseDtoList getAllPosts() {
        List<Article> returnData = boardService.getAllPosts();
        return new ArticleResponseDtoList(returnData);
    }

    @GetMapping("/{id}")
    public ArticleResponseDto getPost(@PathVariable Long id) {
        Article returnData = boardService.getPost(id);
        return new ArticleResponseDto(returnData);
    }

    @PostMapping()
    public ArticleResponseDto writePost(@RequestBody ArticleRequestDto requestDto) {
        Article returnData = boardService.writePost(requestDto);
        return new ArticleResponseDto(returnData);
    }

    @PostMapping("/{id}")
    public ArticleResponseDto checkPw(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        boardService.checkPw(id, requestDto);
        return new ArticleResponseDto(null);
    }

    @PutMapping("/{id}")
    public ArticleResponseDto updatePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        Article returnData = boardService.updatePost(id, requestDto);
        return new ArticleResponseDto(returnData);
    }

    @DeleteMapping("/{id}")
    public ArticleResponseDto deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
        return new ArticleResponseDto(null);
    }
}