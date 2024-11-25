package com.gwantong.project.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gwantong.project.authorization.service.AuthorizationService;
import com.gwantong.project.util.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTAuthorizationInterceptor implements HandlerInterceptor {
        @Autowired
        JWTUtil jwtUtil;
        @Autowired
        AuthorizationService authorizationService;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                        throws Exception {

                if ("OPTIONS".equals(request.getMethod())) {
                        return true;
                }

                String jwtToken = request.getHeader("Jwt");
                String userId = request.getHeader("User-Id");

                log.info("jwt in header : " + jwtToken);
                log.info("userId in header : " + userId);

                // GlobalExceptionHandler가 예외 처리 해줬으니 안심
                authorizationService.authorizeUser(userId, jwtToken);

                request.setAttribute("userId", userId);
                return true;
        }
}