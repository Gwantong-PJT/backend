package com.gwantong.project.authorization.service;

public interface AuthorizationService {
    boolean authorizeUser(String userId, String givenRefreshToken) throws Exception;

    void saveRefreshToken(String userId, String refreshToken);

    void reissuanceRefreshToken(String userId, String refreshToken);
}
