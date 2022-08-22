package com.sparta.week3_1.service;

import com.sparta.week3_1.dto.LoginRequestDto;
import com.sparta.week3_1.dto.SignupRequestDto;
import com.sparta.week3_1.model.User;
import com.sparta.week3_1.repository.UserRepository;
import com.sparta.week3_1.security.JwtTokenUtils;
import com.sparta.week3_1.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String ACCESS_TOKEN_HEADER = "Authorization";
    public static final String REFRESH_TOKEN_HEADER = "refresh-token";
    public static final String TOKEN_TYPE = "BEARER";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public User signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String passwordConfirm = requestDto.getPasswordConfirm();

        // 비밀번호 일치 확인
        if (!passwordConfirm.equals(password)) {
            throw new IllegalArgumentException("비밀번호를 확인해주세요.");
        }
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByNickname(nickname);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 비밀번호 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(nickname, password);
        return userRepository.save(user);
    }

    public User login(LoginRequestDto requestDto, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getNickname(), requestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        final String accessToken = JwtTokenUtils.generateAccessToken(principal);
        response.addHeader(ACCESS_TOKEN_HEADER, TOKEN_TYPE + " " + accessToken);
        final String refreshToken = JwtTokenUtils.generateRefreshToken(principal);
        response.addHeader(REFRESH_TOKEN_HEADER, refreshToken);

        return principal.getUser();
    }
}