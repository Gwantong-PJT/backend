package com.gwantong.project.file.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/uploads")
@RestController
@Tag(name = "파일 유틸리티", description = "핫플 게시글에 첨부된 사진 조회 등을 제공할 예정")
public class FileController {
    @Value("${file.picture.upload-dir}")
    private String uploadDir;

    private String hotplaceDir = "hotplaces/";
    private String profileDir = "profiles/";

    @Operation(summary = "핫플 게시글 사진 조회", description = "게시글에 올라간 사진을 조회한다.<br>파일명에 맞는 사진을 반환해 줌<br>DB에 uploads/hotplaces/{파일명}으로 저장되고 그대로 반환 되기 때문에<br>실제 사용할 땐 요청 결과로 받은 url을 그대로 localhost:8520/뒤에 붙여서 요청하면 됨")
    @GetMapping("/hotplaces/{fileName}")
    public ResponseEntity<?> getPicture(@PathVariable("fileName") String filename) {
        log.info("파일 명 : " + filename);
        log.info("루트 명 : " + uploadDir + hotplaceDir);
        Path filePath = Paths.get(uploadDir + hotplaceDir).resolve(filename);
        try {
            String url = filePath.toUri().toString();
            log.info("target url : " + url);
            UrlResource resource = new UrlResource(url);
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Type", getContentType(filename))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("file read Error");
        }
    }

    @Operation(summary = "프로필 사진 조회", description = "프로필 사진을 조회한다.<br>파일명에 맞는 사진을 반환해 줌<br>DB에 uploads/profiles/{파일명}으로 저장되고 그대로 반환 되기 때문에<br>실제 사용할 땐 요청 결과로 받은 url을 그대로 localhost:8520/뒤에 붙여서 요청하면 됨")
    @GetMapping("/profiles/{fileName}")
    public ResponseEntity<?> getProfile(@PathVariable("fileName") String filename) {
        log.info("파일 명 : " + filename);
        log.info("루트 명 : " + uploadDir + profileDir);
        Path filePath = Paths.get(uploadDir + profileDir).resolve(filename);
        try {
            String url = filePath.toUri().toString();
            log.info("target url : " + url);
            UrlResource resource = new UrlResource(url);
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Type", getContentType(filename))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("file read Error");
        }
    }


    private String getContentType(String filename) {
        String fileExtension = getFileExtension(filename).toLowerCase();
        switch (fileExtension) {
            case "png":
                return "image/png";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            default:
                return "application/octet-stream"; // 알 수 없는 파일 형식일 경우 기본 바이너리 타입
        }
    }
    private String getFileExtension(String filename) {
        int lastIndex = filename.lastIndexOf('.');
        if (lastIndex > 0) {
            return filename.substring(lastIndex + 1);
        }
        return "";
    }
}
