package com.sparta.week3_1.service;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sparta.week3_1.ExceptionHandler.CustomException;
import com.sparta.week3_1.ExceptionHandler.ErrorCode;
import com.sparta.week3_1.entity.RefreshToken;
import com.sparta.week3_1.repository.RefreshTokenRepository;
import com.sparta.week3_1.security.jwt.JwtDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.sparta.week3_1.ExceptionHandler.ErrorCode.INVALID_TOKEN;
import static com.sparta.week3_1.security.jwt.JwtTokenUtils.CLAIM_EXPIRED_DATE;

@Component
@RequiredArgsConstructor
public class RefreshTokenService {

    private final JwtDecoder jwtDecoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public void checkToken(String token) {
        DecodedJWT decodedJWT = jwtDecoder.isValidToken(token).orElseThrow(() -> new JWTDecodeException("유효한 토큰이 아닙니다."));

        Date expiredDate = decodedJWT
                .getClaim(CLAIM_EXPIRED_DATE)
                .asDate();

        Date now = new Date();
        if (expiredDate.before(now)) {
            throw new CustomException(INVALID_TOKEN);
        }

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token);
        if (!refreshToken.getRefreshToken().equals(token)) {
            throw new CustomException(INVALID_TOKEN);
        }
    }

}
