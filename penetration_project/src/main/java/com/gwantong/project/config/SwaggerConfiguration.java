package com.gwantong.project.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@SecurityScheme(name = "Jwt", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, description = "jwt 토큰 입력")
@SecurityScheme(name = "User-Id", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, description = "로그인 된 사용자 ID 입력")
@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {

        // Components compo = new Components()
        // .addSecuritySchemes("jwt", new SecurityScheme()
        // .name("jwt-token").type(SecurityScheme.Type.HTTP)
        // .scheme("bearer")
        // .bearerFormat("JWT"));

        StringBuilder desc = new StringBuilder()
                .append("<b>작성자</b> : 신주환<br>")
                .append("<h4>POST: /user/login(로그인), POST: /user/signup(회원 가입), GET: /user/password(비밀번호 찾기) 이외의 모든 기능은 로그인을 진행하고 나온<br>")
                .append("JWT 토큰과 로그인 한 유저 ID를 Authorize에 넣고 진행</h4>")
                .append("<h4>로그인은 30분간 유효<h4><br>")

                .append("<h2><font color ='red'>")
                .append("사진 업로드 기능 구현 시")
                .append("</font></h2>")
                .append("다음과 같은 방법으로 백에서 multipart/form-data 형식으로 받을 수 있게 !<br>")
                .append("html : form 태그 안에 속성으로 enctype=\"multipart/form-data\" 집어넣기 <br>")
                .append("axios : axios.post(\"/upload\", formData, { headers: { \"Content-Type\": \"multipart/form-data\" } })<br>")

                .append("<h2><font color ='red'>스웨거에서 파일 입출력 테스트 잘 안됨 주의!</font></h2>")

                .append("<h3>응답 코드</h3>")
                .append("200 : 정상 응답<br>")
                .append("204 : 컨텐츠 없음 (파라미터 오류)<br>")
                .append("400 : 잘못된 요청 (파라미터 오류)<br>")
                .append("401 : 인증 실패 (jwt토큰이 잘못 됐거나 userId와 맞지 않음. 로그인 다시 시도)<br>")
                .append("404 : 페이지 없음 (대체로 URL 실수)<br>")
                .append("405 : 메소드 오류 (GET / POST 등 실수)<br>")
                .append("500 : 서버 오류 (연락 바람)<br>");

        Info info = new Info()
                .title("관통 프로젝트 백엔드 API 명세서")
                .description(desc.toString())
                .version("v0.1");

        return new OpenAPI()
                .info(info)
                .security(getSecurityRequirement());
    }

    private List<SecurityRequirement> getSecurityRequirement() {
        List<SecurityRequirement> requirements = new ArrayList<>();
        requirements.add(new SecurityRequirement().addList("Jwt").addList("User-Id"));
        return requirements;
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
        return GroupedOpenApi.builder().group("관광지 정보").pathsToMatch("/attraction/**").build();
    }


    @Bean
    public GroupedOpenApi listApi() {
        return GroupedOpenApi.builder().group("리스트 유틸").pathsToMatch("/list/**").build();
    }

    @Bean
    public GroupedOpenApi fileApi() {
        return GroupedOpenApi.builder().group("파일 유틸").pathsToMatch("/uploads/**").build();
    }
}
