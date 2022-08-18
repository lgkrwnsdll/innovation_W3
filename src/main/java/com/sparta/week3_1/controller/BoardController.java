package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.dto.DataResponseDto;
import com.sparta.week3_1.dto.DatasResponseDto;
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
    public DatasResponseDto getAllPosts() {
        List<Article> returnData = boardService.getAllPosts();
        return new DatasResponseDto(returnData);
    }

    @GetMapping("/{id}")
    public DataResponseDto getPost(@PathVariable Long id) {
        Article returnData = boardService.getPost(id);
        return new DataResponseDto(returnData);
    }

    @PostMapping()
    public DataResponseDto writePost(@RequestBody ArticleRequestDto requestDto) {
        Article returnData = boardService.writePost(requestDto);
        return new DataResponseDto(returnData);
    }

    @PostMapping("/{id}")
    public DataResponseDto checkPw(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        boardService.checkPw(id, requestDto);
        return new DataResponseDto(null);
    }

    @PutMapping("/{id}")
    public DataResponseDto updatePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        Article returnData = boardService.updatePost(id, requestDto);
        return new DataResponseDto(returnData);
    }

    @DeleteMapping("/{id}")
    public DataResponseDto deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
        return new DataResponseDto(null);
    }
}