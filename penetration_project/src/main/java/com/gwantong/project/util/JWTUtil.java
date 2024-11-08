package com.gwantong.project.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTUtil {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.access-token.expiretime}")
    private long accessTokenExpireTime;

    @Value("${jwt.refresh-token.expiretime}")
    private long refreshTokenExpireTime;

    private SecretKey secretKey;

    @PostConstruct
    public void createSecretKey() {
        log.debug(salt);
        // 키 생성
        this.secretKey = new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String createAccessToken(String userId) {
        return createToken(userId, "access-token", accessTokenExpireTime);
    }

    public String createRefreshToken(String userId) {
        return createToken(userId, "refresh-token", refreshTokenExpireTime);
    }

    private String createToken(String userId, String tokenName, long expireTime) {
        // 토큰 이름 넣고, userId 세팅, 생성일/종료일 세팅, 서명 후 인코딩
        return Jwts.builder()
                .subject(tokenName)
                .claim("userId", userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                // .signWith(secretKey, Jwts.SIG.HS512)
                .signWith(secretKey)
                .compact();
    }

    public String parseToken(String jwtToken) {
        // 서명 확인 후 디코딩. 페이로드 가져온다음 userId 확인
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .get("userId", String.class);
    }

}
