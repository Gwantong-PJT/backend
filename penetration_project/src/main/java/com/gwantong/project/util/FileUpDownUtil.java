package com.gwantong.project.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpDownUtil {
    private static String UPLOAD_FOLDER = "uploads/";
    private static String NOTICE_FOLDER = "notices/";

    public boolean uploadNoticeFile(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }

        try {
            // 업로드 경로 확인 및 폴더 생성
            Path path = Paths.get(UPLOAD_FOLDER + NOTICE_FOLDER + file.getOriginalFilename());
            Files.createDirectories(path.getParent());

            // 파일 저장
            file.transferTo(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ResponseEntity<?> downloadNoticeFile(String filename) {
        try {
            Path filepath = Paths.get(UPLOAD_FOLDER + NOTICE_FOLDER + filename);
            File file = filepath.toFile();

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent = Files.readAllBytes(filepath);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + filename);

            return ResponseEntity.ok().headers(headers).body(fileContent);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("파일 입출력 에러");
        }
    }

}
