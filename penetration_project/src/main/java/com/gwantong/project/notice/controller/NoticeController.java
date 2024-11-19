package com.gwantong.project.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.notice.dto.NoticeDto;
import com.gwantong.project.notice.service.NoticeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    @Operation(summary = "공지사항 작성", description = "공지사항을 작성한다.<br>작성자 정보(userNo), 제목(noticeTitle) 필수<br>본문(noticeText)과 첨부파일(noticeFile)은 선택")
    @PostMapping("/")
    public ResponseEntity<?> insertNotice(@RequestBody NoticeDto notice) {
        int result = noticeService.insertNotice(notice);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "공지사항 수정", description = "공지사항을 수정한다.<br>글 번호(noticeNo) 필수<br>제목(noticeTitle), 본문(noticeText)과 첨부파일(noticeFile)은 선택 사항.<br>작성자는 변경 불가")
    @PutMapping("/")
    public ResponseEntity<?> updateNotice(@RequestBody NoticeDto notice) {
        int result = noticeService.updateNotice(notice);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제한다.<br>글 번호(noticeNo)로 해당 글을 삭제한다.<br>")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteNotice(@RequestBody int noticeNo) {
        int result = noticeService.deleteNotice(noticeNo);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
