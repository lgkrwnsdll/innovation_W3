package com.sparta.week3_1.security.jwt;

import com.sparta.week3_1.dto.TokenDto;
import com.sparta.week3_1.entity.RefreshToken;
import com.sparta.week3_1.entity.User;
import com.sparta.week3_1.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;


    private final String JWT_SECRET = "secretKeysecretKeysecretKeysecretKeysecretKeysecretKey";
    private final byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
    private final Key key = Keys.hmacShaKeyFor(keyBytes);

    public TokenDto generateTokenDto(User user) {
        long now = new Date().getTime();

        int ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분
        String accessToken = Jwts.builder()
                .setSubject(user.getNickname())
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        int REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 2주일
        String refreshToken = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        RefreshToken refreshTokenObject = new RefreshToken(user.getNickname(), refreshToken);

        refreshTokenRepository.save(refreshTokenObject);

        return new TokenDto(accessToken, refreshToken);
    }


    public Authentication getAuthentication(String token) {
        String username = Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }


}