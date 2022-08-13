package com.sparta.week3_1.repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sparta.week3_1.entity.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class MemoryBoardRepository {

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class CreatedData {
        String createdAt;
        String modifiedAt;
        Long id;
        String title;
        String content;
        String author;

        public CreatedData(String createdAt, String modifiedAt, Long id, String title, String content, String author) {
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
            this.id = id;
            this.title = title;
            this.content = content;
            this.author = author;
        }
    }
    private static Map<Long, CreatedData> store = new HashMap<>();

    public ArrayList<CreatedData> findAll() {
        return new ArrayList<>(store.values());
    }

    public CreatedData save(Article article, Long id) {
        String curTime = String.valueOf(LocalDateTime.now());
        CreatedData createdData = new CreatedData(curTime, curTime, id, article.getTitle(), article.getContent(), article.getAuthor());
        store.put(id, createdData);
        return createdData;
    }

    // 게시물 조회 & 비번확인
    public CreatedData findById(int id) {
        return store.get(id);
    }
    public CreatedData updateById(int id) {
        return store.get(id);
    }
    public CreatedData deleteById(int id) {
        return store.get(id);
    }

}
