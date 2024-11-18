package com.gwantong.project.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.notice.dto.NoticeDto;
import com.gwantong.project.notice.service.NoticeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/notice")
@Tag(name = "공지사항", description = "공지사항 보기(모든 회원), 쓰기/수정/삭제(관리자 한정)에 관한 클래스(notice_tb)")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @Operation(summary = "모든 공지사항 보기", description = "모든 공지사항 글을 확인한다.<br>페이징은 아직 미구현")
    @GetMapping("/")
    public ResponseEntity<?> viewAllNotices() {
        List<NoticeDto> list = noticeService.viewAllNotices();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "공지사항 조회", description = "조회하고자 하는 공지사항을 선택하면 해당 공지사항의 세부 사항을 조회한다.<br>noticeNo에 공지사항 번호를 담아 전달한다")
    @GetMapping("/{noticeNo}")
    public ResponseEntity<?> viewNotice(@PathVariable("noticeNo") int noticeNo) {
        NoticeDto notice = noticeService.viewNotice(noticeNo);
        return ResponseEntity.ok(notice);
    }
}
