package com.sparta.week3_1.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.week3_1.dto.ExceptionDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sparta.week3_1.ExceptionHandler.ErrorCode.INVALID_TOKEN;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {

        Object invalidJwt = request.getAttribute("INVALID_JWT");

        if (invalidJwt != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            ExceptionDto msg = new ExceptionDto(INVALID_TOKEN);

            String result = objectMapper.writeValueAsString(msg);
            response.getWriter().write(result);
        }

    }
}
