package com.gwantong.project.hotplace.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gwantong.project.hotplace.dto.CommentDto;
import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.dto.HotplacePictureDto;
import com.gwantong.project.hotplace.service.HotplaceService;
import com.gwantong.project.util.FileUpDownUtil;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/hotplace")
@Tag(name = "핫플레이스", description = "핫플레이스 글 보기, 쓰기, 수정, 삭제 등 (hotplaces_tb)")
public class HotplaceController {
    @Autowired
    HotplaceService hotplacesService;
    @Autowired
    FileUpDownUtil fileUpDownUtil;

    @Operation(summary = "모든 글 보기", description = "모든 글을 조회 한다.<br>페이징은 아직 미구현")
    @GetMapping("/")
    public ResponseEntity<?> viewAllHotplaces() {
        List<HotplaceDto> list = hotplacesService.viewAllHotplaces();
        if (list != null) {
            log.info("모든 핫플 보기 디버깅: " + list.get(0).toString());
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "특정 글 보기", description = "파라미터로 글 번호(hotplaceNo)를 넘기면<br>해당 글을 상세 조회<br>해당 글에 올라간 댓글과 사진들도 같이 조회 됨")
    @GetMapping("/{hotplaceNo}")
    public ResponseEntity<?> selectHotplace(@PathVariable("hotplaceNo") int hotplaceNo) {
        log.info("특정 글 보기 메소드 들어옴");
        HotplaceDto hotpl = hotplacesService.selectHotplace(hotplaceNo);
        if (hotpl != null) {
            log.info("핫플 세부 보기 디버깅: " + hotpl.toString());
            return ResponseEntity.ok(hotpl);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "사용자가 작성한 글 보기", description = "파라미터로 사용자 번호(userNo)를 넘기면<br>해당 유저가 올린 핫플 게시글이 표시된다.<br>")
    @GetMapping("/user/{userNo}")
    public ResponseEntity<?> usersHotplaces(@PathVariable("userNo") int userNo) {
        List<HotplaceDto> hotpls = hotplacesService.usersHotplaces(userNo);
        //
        if (hotpls != null) {
            return ResponseEntity.ok(hotpls);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /// deprecated
    @Hidden
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

    @Transactional
    @Operation(summary = "글 쓰기 + 사진 첨부", description = "글 쓰기를 진행한다.<br>PK는 글 번호(hotplaceNo)지만 자동 입력되므로 입력할 필요 X<br>유저 정보(userNo)는 필수, 로그인 된 사용자로 넘기기<br>나머진 필수 아님<br><b>스웨거로 사진 업로드 테스트가 잘 안됨. 근데 기능은 정상 작동 함</b>")
    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertHotplace(
            @RequestParam("userNo") int userNo,
            @RequestParam(value = "hotplaceTitle", required = false) String hotplaceTitle,
            @RequestParam(value = "hotplaceText", required = false) String hotplaceText,
            @RequestParam(value = "latitude", defaultValue = "0") double latitude,
            @RequestParam(value = "longitude", defaultValue = "0") double longitude,
            @RequestParam(value = "pictures", required = false) MultipartFile[] pictures) {

        HotplaceDto hotplaceDto = new HotplaceDto();
        hotplaceDto.setUserNo(userNo);
        hotplaceDto.setHotplaceTitle(hotplaceTitle);
        hotplaceDto.setHotplaceText(hotplaceText);
        hotplaceDto.setLatitude(latitude);
        hotplaceDto.setLongitude(longitude);

        int result = hotplacesService.insertHotplace(hotplaceDto);
        if (result != 1) {
            return ResponseEntity.badRequest().body("fail to insert text");
        }

        if (pictures != null) {
            String[] pictureUrls = fileUpDownUtil.uploadHotplacePicture(pictures);
            if (pictureUrls == null) {
                return ResponseEntity.internalServerError().body("fail to upload pictures in system");
            }

            List<HotplacePictureDto> pictureDtos = new ArrayList<>();
            for (String url : pictureUrls) {
                HotplacePictureDto hotplpic = new HotplacePictureDto();
                hotplpic.setHotplaceNo(hotplaceDto.getHotplaceNo());
                hotplpic.setPictureUrl(url);
                pictureDtos.add(hotplpic);
            }
            int uploadResult = hotplacesService.uploadPicture(pictureDtos);
            if (uploadResult != pictureUrls.length) {
                return ResponseEntity.internalServerError().body("fail to upload pictures in DB");
            }
        }

        return ResponseEntity.ok("all process success");
    }

    @Operation(summary = "글 수정", description = "글 수정을 진행한다.<br>글 번호에 해당되는 글의 내용을 수정(hotplaceNo 필수)<br>제목, 본문, 위도/경도 중 변경을 원하는 정보 보내기<br> 날짜는 갱신시각으로 자동 교체 됨<br>작성자(회원번호 userNo)는 변경 불가")
    @PutMapping("/")
    public ResponseEntity<?> updateHotplace(@RequestBody HotplaceDto hotpl) {
        log.info(hotpl.toString());
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

    // ==============================
    // ============= 댓글 ===========
    // ==============================

    @Operation(summary = "댓글 쓰기", description = "댓글 쓰기를 진행한다.<br>댓글 번호는 자동 입력되므로 입력할 필요 X<br>핫플 번호(hotplaceNo), 유저 정보(userNo)는 필수<br> - 현재 로그인 된 사용자랑 보고있는 글 번호 넘기기<br>본문 공백도 상관없음")
    @PostMapping("/comment")
    public ResponseEntity<?> insertComment(@RequestBody CommentDto comment) {
        int result = hotplacesService.insertComment(comment);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정을 진행한다.<br>댓글 번호에 해당되는 댓글의 내용을 수정(commentNo 필수)<br>본문만 수정 가능. 날짜는 갱신시각으로 자동 교체 됨<br>")
    @PutMapping("/comment")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto comment) {
        int result = hotplacesService.updateComment(comment);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제를 진행한다.<br>댓글 번호에 해당되는 글을 삭제(commentNo 필수)<br>")
    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestBody int comment) {
        int result = hotplacesService.deleteComment(comment);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
