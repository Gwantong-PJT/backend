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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/uploads")
@RestController
public class FileController {
    @Value("${file.picture.upload-dir}")
    private String uploadDir;

    @GetMapping("/hotplaces/{fileName}")
    public ResponseEntity<?> getPicture(@PathVariable("fileName") String filename) {
        log.info("파일 명 : " + filename);
        log.info("루트 명 : " + uploadDir);
        Path filePath = Paths.get(uploadDir).resolve(filename);
        try {
            String url = filePath.toUri().toString();
            log.info("target url : " + url);
            UrlResource resource = new UrlResource(url);
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Type", "image/png")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("file read Error");
        }
    }

}
