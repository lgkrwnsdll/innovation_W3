package com.sparta.week3_1.dto;

import com.sparta.week3_1.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ArticleResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Single {
        private boolean success;
        private Article data;
        private Object error;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Multi {
        private boolean success;
        private List<Article> data;
        private Object error;
    }

    @Getter
    public static class NullId {
        private final boolean success = false;
        private final Article data = null;
        private final Object error = "NULL_ID";
    }


}