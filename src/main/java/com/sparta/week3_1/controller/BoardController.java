package com.sparta.week3_1.controller;

import com.sparta.week3_1.ExceptionHandler.CustomException;
import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.dto.DataResponseDto;
import com.sparta.week3_1.dto.DatasResponseDto;
import com.sparta.week3_1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.sparta.week3_1.ExceptionHandler.ErrorCode.WRONG_PASSWORD;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class BoardController {
    private final BoardService boardService;

    @GetMapping()
    public DatasResponseDto getAllPosts() {
        return new DatasResponseDto(boardService.getAllPosts());
    }

    @GetMapping("/{id}")
    public DataResponseDto getPost(@PathVariable Long id) {
        return new DataResponseDto(boardService.getPost(id));
    }

    @PostMapping()
    public DataResponseDto writePost(@RequestBody ArticleRequestDto requestDto) {
        return new DataResponseDto(boardService.writePost(requestDto));
    }

    @PostMapping("/{id}")
    public DataResponseDto checkPw(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        if (requestDto.getPassword() == boardService.getPost(id).getPassword()) { // 일치
            return new DataResponseDto(null);
        } else { // 불일치
            throw new CustomException(WRONG_PASSWORD);
        }
    }

    @PutMapping("/{id}")
    public DataResponseDto updatePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return new DataResponseDto(boardService.updatePost(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public DataResponseDto deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
        return new DataResponseDto(null);
    }
}