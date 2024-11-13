package com.gwantong.project.hotplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "CommentDto (댓글)", description = "핫플에 달린 댓글들. comment_tb 내용과 동일")
@Data
public class CommentDto {
    @Schema(description = "댓글 번호", requiredMode = Schema.RequiredMode.AUTO)
    private int commentNo;

    @Schema(description = "핫플 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private int hotplaceNo;

    @Schema(description = "회원 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userNo;

    @Schema(description = "댓글 본문")
    private String commentText;

    @Schema(description = "댓글 작성 시각")
    private String commentDate;
}
