package com.sparta.week3_1.repository;

import com.sparta.week3_1.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Article, Long> {

}
