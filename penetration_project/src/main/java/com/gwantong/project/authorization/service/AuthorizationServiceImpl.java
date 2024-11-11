package com.gwantong.project.authorization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwantong.project.authorization.mapper.AuthorizationMapper;
import com.gwantong.project.exception.UnauthorizedException;
import com.gwantong.project.util.JWTUtil;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    AuthorizationMapper authorizationMapper;
    @Autowired
    JWTUtil jwtUtil;

    @Override
    public boolean authorizeUser(String userId, String givenRefreshToken) throws Exception {
        String dbRefreshToken = authorizationMapper.authorizeUser(userId);
        if (dbRefreshToken == null) {
            throw new UnauthorizedException("refresh token doesn't exist in DB");
        }
        if (!(dbRefreshToken.equals(givenRefreshToken))) {
            throw new UnauthorizedException("refresh token doesn't match");
        }

        String tokenUserId = jwtUtil.parseToken(givenRefreshToken);
        if (tokenUserId == null || !(tokenUserId.equals(userId))) {
            throw new UnauthorizedException("user id don't match");
        }

        return true;
    }

    @Override
    @Transactional
    public String generateFreshToken(String userId) {
        String jwt = jwtUtil.createRefreshToken(userId);
        saveRefreshToken(userId, jwt);
        return jwt;
    }

    private void saveRefreshToken(String userId, String refreshToken) {
        String isThereKey = authorizationMapper.authorizeUser(userId);

        if (isThereKey == null || isThereKey.equals("")) {
            // 컬럼이 없다면 => insert 수행
            if (authorizationMapper.saveRefreshToken(userId, refreshToken) != 1) {
                throw new UnauthorizedException("fail to save refresh token");
            }
        } else {
            // 컬럼이 있다면 => update 수행
            if (authorizationMapper.reissuanceRefreshToken(userId, refreshToken) != 1) {
                throw new UnauthorizedException("fail to reissuance refresh token");
            }
        }
    }
}
