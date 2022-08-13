package com.sparta.week3_1.service;

import lombok.Getter;

import java.util.List;

@Getter
public class MultiResponse<T> extends CommonResponse {
    List<T> data;
}
