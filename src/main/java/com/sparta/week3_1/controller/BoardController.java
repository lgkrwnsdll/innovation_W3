package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.ArticleRequestDto;
import com.sparta.week3_1.repository.BoardRepository;
import com.sparta.week3_1.entity.Article;
import com.sparta.week3_1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController // @Controller + @Responsebody
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class BoardController {

    private final BoardRepository boardRepository; // 전체 조회, 등록, id로 삭제 기능
    private final BoardService boardService; // id로 조회, id로 수정 기능
    private final ResponseService responseService; // 성공 여부, 에러 메세지 포함한 응답 전달 기능


//    Map<String, Object> msg = new LinkedHashMap<>() {{
//        put("success", true);
//        put("data", true);
//        put("error", null);
//    }};

    @GetMapping()
    public MultiResponse<Article> getAllPosts() {
//        msg.replace("success", true);
//        msg.replace("data", boardRepository.findAllByOrderByIdDesc());
//        msg.replace("error", null);
//        return msg;
        return responseService.getMultiResponse(boardRepository.findAllByOrderByIdDesc());
    }

    @GetMapping("/{id}")
    public SingleResponse<Article> getPost(@PathVariable Long id) {
//        msg.replace("success", true);
//        msg.replace("data", boardService.findById(id));
//        msg.replace("error", null);
//        return msg;
        return responseService.getSingleResponse(boardService.findById(id));
    }

    @PostMapping()
    public SingleResponse<Article> writePost(@RequestBody ArticleRequestDto requestDto) {
        Article article = new Article(requestDto); // requestDto가 데이터를 불러와서 article에 담음
//        msg.replace("success", true);
//        msg.replace("data", boardRepository.save(article));
//        msg.replace("error", null);
//        return msg;
        return responseService.getSingleResponse(boardRepository.save(article));
    }

    @PostMapping("/{id}")
    public SingleResponse<Article> checkPw(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        if (requestDto.getPassword() != boardService.findById(id).getPassword()) { // 불일치
//            msg.replace("success", false);
//            msg.replace("data", false);
//            msg.replace("error", "wrong password");
            return responseService.getWrongPwResponse();
        } else { // 일치
//            msg.replace("success", true);
//            msg.replace("data", true);
//            msg.replace("error", null);
            return responseService.getSingleResponse(null);
        }
//        return msg;
    }

    @PutMapping("/{id}")
    public SingleResponse<Article> updatePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
//        msg.replace("success", true);
//        msg.replace("data", boardService.update(id, requestDto));
//        msg.replace("error", null);
//        return msg;
        return responseService.getSingleResponse(boardService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public SingleResponse<Article> deletePost(@PathVariable Long id) {
        boardRepository.deleteById(id);
//        msg.replace("success", true);
//        msg.replace("data", true);
//        msg.replace("error", null);
//        return msg;
        return responseService.getSingleResponse(null);
    }
}