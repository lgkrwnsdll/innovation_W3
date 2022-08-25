package com.sparta.week3_1.security.jwt;

import com.sparta.week3_1.dto.TokenDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtAuthFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            TokenDto jwt = getJwtFromRequest(request);
            validateToken(request, jwt.getAccesstoken(), jwt.getRefreshtoken());
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private void validateToken(HttpServletRequest request, String accessToken, String refreshToken) {
        if (accessToken != null && jwtProvider.validateToken(accessToken) && jwtProvider.validateToken(refreshToken)) {
            Authentication auth = jwtProvider.getAuthentication(accessToken);
            // 정상 토큰이면 토큰을 통해 생성한 Authentication 객체를 SecurityContext에 저장 -> Controller에서 @AuthenticationPrincipal로 받아올 수 있음
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            request.setAttribute("INVALID_JWT", "INVALID_JWT");
        }
    }

    private TokenDto getJwtFromRequest(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        String refreshToken = request.getHeader("refresh-token");
        if (refreshToken != null && accessToken.startsWith("BEARER ")) {
            return new TokenDto(accessToken.substring("BEARER ".length()), refreshToken);
        }
        return null;
    }
}
