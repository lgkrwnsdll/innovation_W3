package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.entity.Article;
import com.sparta.week3_1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller + @Responsebody
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class BoardController {
    private final BoardService boardService;
    private final ResponseService responseService; // 성공 여부, 에러 메세지 포함한 응답 전달 기능


    @GetMapping()
    public MultiResponse<Article> getAllPosts() {
        return responseService.getMultiResponse(boardService.getAllPosts());
    }

    @GetMapping("/{id}")
    public SingleResponse<Article> getPost(@PathVariable Long id) {
        return responseService.getSingleResponse(boardService.getPost(id));
    }

    @PostMapping()
    public SingleResponse<Article> writePost(@RequestBody ArticleRequestDto requestDto) {
        return responseService.getSingleResponse(boardService.writePost(requestDto));
    }

    @PostMapping("/{id}")
    public SingleResponse<Article> checkPw(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        if (requestDto.getPassword() != boardService.getPost(id).getPassword()) { // 불일치
            return responseService.getWrongPwResponse();
        } else { // 일치
            return responseService.getSingleResponse(null);
        }
    }

    @PutMapping("/{id}")
    public SingleResponse<Article> updatePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return responseService.getSingleResponse(boardService.updatePost(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public SingleResponse<Article> deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
        return responseService.getSingleResponse(null);
    }
}