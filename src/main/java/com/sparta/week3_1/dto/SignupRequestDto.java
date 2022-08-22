package com.sparta.week3_1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String passwordConfirm;
}
