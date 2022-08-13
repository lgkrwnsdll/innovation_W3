package com.sparta.week3_1.repository;

import com.sparta.week3_1.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc(); // 모두 불러와 id에 대해 내림차순 정렬
}
