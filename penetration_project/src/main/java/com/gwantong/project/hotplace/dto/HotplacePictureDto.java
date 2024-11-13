package com.gwantong.project.hotplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "HotplacePictureDto (사진)", description = "핫플의 사진")
@Data
public class HotplacePictureDto {
    @Schema(description = "핫플 사진 번호", requiredMode = Schema.RequiredMode.AUTO)
    private int pictureNo;

    @Schema(description = "핫플 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private int hotplaceNo;

    @Schema(description = "사진 (링크 형태로 제공 예정)")
    private String pictureUrl;
}
