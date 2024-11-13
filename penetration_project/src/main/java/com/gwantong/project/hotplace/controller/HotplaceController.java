package com.gwantong.project.hotplace.controller;

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

import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.service.HotplaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/hotplace")
@Tag(name = "핫플레이스", description = "핫플레이스 글 보기, 쓰기, 수정, 삭제 등 (hotplaces_tb)")
public class HotplaceController {
    @Autowired
    HotplaceService hotplacesService;

    @Operation(summary = "모든 글 보기", description = "모든 글을 조회 한다.<br>페이징은 아직 미구현")
    @GetMapping("/")
    public ResponseEntity<?> viewAllHotplaces() {
        List<HotplaceDto> list = hotplacesService.viewAllHotplaces();
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "특정 글 보기", description = "파라미터로 글 번호(int hotpl_no = hotplaceNo)를 넘기면<br>해당 글을 상세 조회")
    @GetMapping("/{hotpl_no}")
    public ResponseEntity<?> selectHotplace(@PathVariable("hotpl_no") int hotpl_no) {
        HotplaceDto hotpl = hotplacesService.selectHotplace(hotpl_no);
        if (hotpl != null) {
            return ResponseEntity.ok(hotpl);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "글 쓰기", description = "글 쓰기를 진행한다.<br>PK는 글 번호(hotplaceNo)지만 자동 입력되므로 입력할 필요 X<br>유저 정보(userNo)는 필수, 로그인 된 사용자로 넘기기<br>나머진 필수 아님")
    @PostMapping("/")
    public ResponseEntity<?> insertHotplace(@RequestBody HotplaceDto hotpl) {
        int result = hotplacesService.insertHotplace(hotpl);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "글 수정", description = "글 수정을 진행한다.<br>글 번호에 해당되는 글의 내용을 수정(hotplaceNo 필수)<br>글 제목과 본문만 수정 가능. 날짜는 갱신시각으로 자동 교체 됨<br>")
    @PutMapping("/")
    public ResponseEntity<?> updateHotplace(@RequestBody HotplaceDto hotpl) {
        int result = hotplacesService.updateHotplace(hotpl);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "글 삭제", description = "글 삭제를 진행한다.<br>글 번호에 해당되는 글을 삭제(hotplaceNo 필수)<br>")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteHotplace(@RequestBody int hotpl) {
        int result = hotplacesService.deleteHotplace(hotpl);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

}
