package com.sparta.week3_1.security;

import com.sparta.week3_1.security.jwt.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String ACCESS_TOKEN_HEADER = "Authorization";
    public static final String REFRESH_TOKEN_HEADER = "refresh-token";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성
        final String accessToken = JwtTokenUtils.generateAccessToken(userDetails);
        response.addHeader(ACCESS_TOKEN_HEADER, "BEARER " + accessToken);
        final String refreshToken = JwtTokenUtils.generateRefreshToken(userDetails);
        response.addHeader(REFRESH_TOKEN_HEADER, refreshToken);
    }

}
