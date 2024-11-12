package com.gwantong.project.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "UserDto (회원 정보)", description = "회원 정보. users_tb의 내용과 동일")
@Data
public class UserDto {
    @Schema(description = "회원 번호", requiredMode = Schema.RequiredMode.AUTO)
    private int userNo;

    @Schema(description = "아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userPassword;

    @Schema(description = "이름")
    private String userName;

    @Schema(description = "권한 (USER / ADMIN)")
    private String userRole;

    @Schema(description = "프로필 사진 링크")
    private String userProfile;

    @Schema(description = "나이 번호")
    private int ageNo;

    @Schema(description = "거주 지역")
    private int userResidence;

    @Schema(description = "나이 (10대, 20대...)")
    private int ageValue;
}
