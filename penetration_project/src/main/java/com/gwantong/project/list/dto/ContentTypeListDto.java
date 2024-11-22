package com.gwantong.project.list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "ContentTypeListDto (카테고리 리스트)", description = "카테고리 정보. contenttypes의 내용")
@Data
public class ContentTypeListDto {

    @Schema(description = "카테고리 번호. 데이터 주고 받을 때 이 코드 이용할 것")
    private int contentTypeId;

    @Schema(description = "카테고리 명")
    private String contentTypeName;
}
