package com.gwantong.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gwantong.project.interceptor.JWTAuthorizationInterceptor;
import com.gwantong.project.interceptor.UserIdInterceptor;

//기존 servlet-context를 대체(Web)
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private JWTAuthorizationInterceptor jwtInterceptor;
    @Autowired
    private UserIdInterceptor userIdInterceptor;

    // CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 요청 경로
                .allowedOrigins("http://localhost:5173", "http://localhost:8520") // 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 쿠키를 포함한 요청 허용
    }

    // Interceptor 설정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그인 없을 때 사용자 받는 인터셉터.
        // registry.addInterceptor(userIdInterceptor).addPathPatterns("/**").order(1);

        // 로그인 인터셉터. 필요 시 아래 전부 주석 해제
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/hotplace/**")
                .addPathPatterns("/attraction/**")
                .addPathPatterns("/notice/**")
                .addPathPatterns("/uploads/**")
                .excludePathPatterns("/user/password")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/signup")
                .order(1);

    }

    // ResourceHandler 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    // ViewControllers 설정 => DB연결 없는 단순한 페이지 이동 설정
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/").setViewName("index2");
    }
}
