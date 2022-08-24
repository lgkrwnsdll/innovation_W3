package com.sparta.week3_1.security.jwt;

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
            String jwt = getJwtFromRequest(request); //request에서 jwt 토큰을 꺼낸다.
            if (jwt != null && jwtProvider.validateToken(jwt)) {
                Authentication auth = jwtProvider.getAuthentication(jwt);
                // 정상 토큰이면 토큰을 통해 생성한 Authentication 객체를 SecurityContext에 저장 -> Controller에서 @AuthenticationPrincipal로 받아올 수 있음
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                if (jwt != null) {
                    request.setAttribute("unauthorization", "401 인증키 없음.");
                }

                if (jwtProvider.validateToken(jwt)) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료.");
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("BEARER ")) {
            return bearerToken.substring("BEARER ".length());
        }
        return null;
    }
}
