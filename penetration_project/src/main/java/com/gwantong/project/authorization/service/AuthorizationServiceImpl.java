package com.gwantong.project.authorization.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gwantong.project.authorization.mapper.AuthorizationMapper;
import com.gwantong.project.exception.UnauthorizedException;
import com.gwantong.project.util.JWTUtil;

public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    AuthorizationMapper authorizationMapper;
    @Autowired
    JWTUtil jwtUtil;

    @Override
    public boolean authorizeUser(String userId, String givenRefreshToken) throws Exception {
        String dbRefreshToken = authorizationMapper.authorizeUser(userId);
        if (dbRefreshToken == null || !(dbRefreshToken.equals(givenRefreshToken))) {
            throw new UnauthorizedException("refresh key doesn't match");
        }

        String tokenId = jwtUtil.parseToken(userId);
        if (tokenId == null || !(tokenId.equals(userId))) {
            throw new UnauthorizedException("id doens't match");
        }

        return true;
    }

    @Override
    public void saveRefreshToken(String userId, String refreshToken) {
        int result = authorizationMapper.saveRefreshToken(userId, refreshToken);
        if (result != 1) {
            throw new UnauthorizedException("fail to save refresh token");
        }
    }

    @Override
    public void reissuanceRefreshToken(String userId, String refreshToken) {
        int result = authorizationMapper.reissuanceRefreshToken(userId, refreshToken);
        if (result != 1) {
            throw new UnauthorizedException("fail to reissuance refresh token");
        }
    }

}
