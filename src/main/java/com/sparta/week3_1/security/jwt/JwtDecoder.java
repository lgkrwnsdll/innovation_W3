package com.sparta.week3_1.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sparta.week3_1.ExceptionHandler.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.sparta.week3_1.ExceptionHandler.ErrorCode.INVALID_TOKEN;
import static com.sparta.week3_1.security.jwt.JwtTokenUtils.*;

@Component
public class JwtDecoder {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String decodeUsername(String token) {
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new CustomException(INVALID_TOKEN));

        Date expiredDate = decodedJWT
                .getClaim(CLAIM_EXPIRED_DATE)
                .asDate();

        Date now = new Date();
        if (expiredDate.before(now)) {
            throw new CustomException(INVALID_TOKEN);
        }

        String username = decodedJWT
                .getClaim(CLAIM_USER_NAME)
                .asString();

        return username;
    }

    public Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT jwt = null;

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT
                .require(algorithm)
                .build();

        jwt = verifier.verify(token);

        return Optional.ofNullable(jwt);
    }
}
