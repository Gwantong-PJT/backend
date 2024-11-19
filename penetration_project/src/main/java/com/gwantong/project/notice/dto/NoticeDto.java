package com.gwantong.project.notice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "NoticeDto (공지사항)", description = "공지사항. notice_tb의 내용과 동일")
@Data
public class NoticeDto {
    @Schema(description = "공지사항 글 번호")
    private int noticeNo;

    @Schema(description = "작성자 번호")
    private int userNo;

    @Schema(description = "공지사항 제목")
    private String noticeTitle;

    @Schema(description = "공지사항 본문")
    private String noticeText;

    @Schema(description = "공지사항 작성일")
    private String noticeDate;

    @Schema(description = "공지사항 첨부파일(링크)")
    private String noticeFile;
}
