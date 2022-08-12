package com.sparta.week3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

    MemoryBoardRepository memoryBoardRepository;

    @Autowired
    public BoardService(MemoryBoardRepository memoryBoardRepository) {
        this.memoryBoardRepository = memoryBoardRepository;
    }

    public ArrayList<MemoryBoardRepository.CreatedData> getAllPosts() {
        return memoryBoardRepository.findAll();
    }

    public MemoryBoardRepository.CreatedData getPost(int id) {
        return memoryBoardRepository.findById(id);
    }

    public MemoryBoardRepository.CreatedData post(Article article, int id) {
        return memoryBoardRepository.save(article, id);
    }
}
