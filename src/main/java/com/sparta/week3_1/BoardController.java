package com.sparta.week3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class BoardController {

    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    private int id = 0;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    @GetMapping()
    @ResponseBody
    public List<MemoryBoardRepository.CreatedData> getAllPosts() {
        return boardService.getAllPosts();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MemoryBoardRepository.CreatedData getPost(@PathVariable int id) {
        return boardService.getPost(id);
    }

    @PostMapping()
    @ResponseBody
    public MemoryBoardRepository.CreatedData writePost(@RequestBody Article article) {
        id++;
        return boardService.post(article, id);
    }
//
//    @PostMapping("/{id}")
//    @ResponseBody
//    public Article checkPw(@PathVariable int id, @RequestBody Article article) {
//        ...
//    }
//
//    @PutMapping("/{id}")
//    @ResponseBody
//    public Article updatePost(@PathVariable int id, @RequestBody Article article) {
//        ...
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseBody
//    public Article deletePost(@PathVariable int id) {
//        ...
//    }
}