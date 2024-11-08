package com.gwantong.project.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gwantong.project.util.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String jwtToken = request.getHeader("Jwt-Access");

        // GlobalExceptionHandler가 예외 처리 해줬으니 안심
        jwtUtil.parseToken(jwtToken);

        return true;
    }
}
