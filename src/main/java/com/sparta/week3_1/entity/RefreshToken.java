package com.sparta.week3_1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken extends Timestamped {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String refreshToken;

    public RefreshToken(String nickname, String refreshToken) {
        this.nickname = nickname;
        this.refreshToken = refreshToken;
    }
}
