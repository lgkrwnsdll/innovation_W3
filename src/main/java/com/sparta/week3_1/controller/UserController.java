package com.sparta.week3_1.controller;

import com.sparta.week3_1.dto.LoginRequestDto;
import com.sparta.week3_1.dto.SignupRequestDto;
import com.sparta.week3_1.dto.UserResponseDto;
import com.sparta.week3_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody SignupRequestDto requestDto) {
        return new UserResponseDto(userService.signup(requestDto));
    }

    // 로그인
    @PostMapping("/login")
    public UserResponseDto login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        return new UserResponseDto(userService.login(requestDto, response));
    }
}
