package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.repository.BoardRepository;
import com.sparta.week3_1.entity.Article;
import com.sparta.week3_1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class BoardController {

    private final BoardRepository boardRepository; // 전체 조회, 등록, 삭제 기능
    private final BoardService boardService; // id로 조회, 수정 기능


    @GetMapping()
    @ResponseBody
    public List<Article> getAllPosts() {
        return boardRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Article getPost(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PostMapping()
    @ResponseBody
    public Article writePost(@RequestBody ArticleRequestDto requestDto) {
        Article article = new Article(requestDto); // requestDto가 데이터를 불러와서 article에 담음
        return boardRepository.save(article);
    }

//    @PostMapping("/{id}")
//    @ResponseBody
//    public Article checkPw(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
//        ...
//    }


    @PutMapping("/{id}")
    public Article updatePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}