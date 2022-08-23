package com.sparta.week3_1.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sparta.week3_1.security.UserDetailsImpl;

import java.util.Date;

public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    private static final int ACCESS_TOKEN_VALID_SEC = 30 * MINUTE;
    private static final int REFRESH_TOKEN_VALID_SEC = 14 * DAY;

    private static final int ACCESS_TOKEN_VALID_MILLI_SEC = ACCESS_TOKEN_VALID_SEC * 1000;
    private static final int REFRESH_TOKEN_VALID_MILLI_SEC = REFRESH_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String JWT_SECRET = "jwt_secret_!@#$%";
    public static String token = null;
    public static String generateAccessToken(UserDetailsImpl userDetails) {

        try {
            token = JWT.create()
                    .withIssuer("sparta")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                     // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_MILLI_SEC))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

    public static String generateRefreshToken() {
        try {
            token = JWT.create()
                    .withIssuer("sparta")
                    //.withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALID_MILLI_SEC))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }
}
