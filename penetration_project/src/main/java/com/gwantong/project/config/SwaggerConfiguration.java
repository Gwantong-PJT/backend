package com.gwantong.project.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("관통 프로젝트 백엔드 API 명세서")
                .description(
                        "<b>작성자</b> : 신주환")
                .version("v0.1");

        return new OpenAPI().components(new Components()).info(info);
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder().group("모든 API").pathsToMatch("/**").build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder().group("유저").pathsToMatch("/user/**").build();
    }

    @Bean
    public GroupedOpenApi hotplaceApi() {
        return GroupedOpenApi.builder().group("핫플").pathsToMatch("/hotplace/**").build();
    }

    @Bean
    public GroupedOpenApi attractionApi() {
        return GroupedOpenApi.builder().group("관광지정보").pathsToMatch("/attraction/**").build();
    }
}
