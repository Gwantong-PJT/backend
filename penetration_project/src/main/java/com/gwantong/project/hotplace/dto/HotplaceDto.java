package com.gwantong.project.hotplace.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "HotplaceDto (핫플 게시글)", description = "핫플 정보. hotplaces_tb의 내용과 동일")
@Data
public class HotplaceDto {
    @Schema(description = "핫플 번호", requiredMode = Schema.RequiredMode.AUTO)
    private int hotplaceNo;

    @Schema(description = "회원 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private int userNo;

    @Schema(description = "회원 이름 (조회시에 조인해서 넘어옴. 입력 필요 X)")
    private String userName;

    @Schema(description = "게시글 제목")
    private String hotplaceTitle;

    @Schema(description = "게시글 본문")
    private String hotplaceText;

    @Schema(description = "게시글 작성 시각")
    private String hotplaceDate;

    @Schema(description = "조회수")
    private int hotplaceViews;

    @Schema(description = "댓글들")
    private List<CommentDto> comments;

    @Schema(description = "사진들")
    private List<HotplacePictureDto> pictures;
}
