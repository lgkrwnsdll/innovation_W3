package com.sparta.week3_1.dto;

import com.sparta.week3_1.ExceptionHandler.ErrorCode;
import com.sparta.week3_1.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final boolean success = true;
    private final User data;
    private final ErrorCode error = null;

    public UserResponseDto(User user) {
        this.data = user;
    }
}
