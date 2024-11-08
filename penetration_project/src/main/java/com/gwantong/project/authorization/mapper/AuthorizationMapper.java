package com.gwantong.project.authorization.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorizationMapper {
    String authorizeUser(String userId);

    int saveRefreshToken(String userId, String refreshToken);

    int reissuanceRefreshToken(String userId, String refreshToken);
}
