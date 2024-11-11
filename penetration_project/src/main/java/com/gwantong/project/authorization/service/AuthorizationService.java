package com.gwantong.project.authorization.service;

public interface AuthorizationService {
    boolean authorizeUser(String userId, String givenRefreshToken) throws Exception;

    String generateFreshToken(String userId);
}
