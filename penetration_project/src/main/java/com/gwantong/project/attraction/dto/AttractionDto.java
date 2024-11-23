package com.gwantong.project.attraction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "AttractionDto (관광지 정보)", description = "관광지 정보. attractions의 내용과 동일")
@Data
public class AttractionDto {
    @Schema(description = "관광지 번호", requiredMode = Schema.RequiredMode.AUTO)
    private int attractionNo;

    @Schema(description = "관광지 코드")
    private int contentId;

    @Schema(description = "이름")
    private String title;

    @Schema(description = "카테고리")
    private int contentTypeId;

    @Schema(description = "지역 번호 (시,도)")
    private int areaCode;

    @Schema(description = "군구 번호 ")
    private int siGunGuCode;

    @Schema(description = "사진 1")
    private String firstImage1;

    @Schema(description = "사진 2")
    private String firstImage2;

    @Schema(description = "???")
    private int mapLevel;

    @Schema(description = "위도")
    private double latitude;

    @Schema(description = "경도")
    private double longitude;

    @Schema(description = "전화 번호")
    private String tel;

    @Schema(description = "주소 1")
    private String addr1;

    @Schema(description = "주소 2")
    private String addr2;

    @Schema(description = "홈페이지 주소")
    private String homepage;

    @Schema(description = "개요")
    private String overview;

    @Schema(description = "좋아요")
    private boolean liked;
}
