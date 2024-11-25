package com.gwantong.project.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUpDownUtil {
    private static String UPLOAD_FOLDER = "uploads/";
    private static String NOTICE_FOLDER = "notices/";
    private static String HOTPLACE_FOLDER = "hotplaces/";
    private static String PROFILE_FOLDER = "profiles/";

    public String[] uploadHotplacePicture(List<MultipartFile> pictures) {
        String[] pictureNames = new String[pictures.size()];
        int idx = 0;
        try {
            for (MultipartFile picture : pictures) {
                // 업로드 경로 확인
                String uniqueFileName = UUID.randomUUID().toString() + "-" + picture.getOriginalFilename();
                String uniqueFileURL = UPLOAD_FOLDER + HOTPLACE_FOLDER + uniqueFileName;
                Path path = Paths.get(uniqueFileURL);

                // 디렉토리 없으면 만들기
                Files.createDirectories(path.getParent());
                // 파일 저장
                picture.transferTo(path);

                pictureNames[idx++] = uniqueFileURL;
            }
        } catch (Exception e) {
            return null;
        }

        return pictureNames;
    }

    public String uploadUserProfilePicture(MultipartFile picture) {
        String uniqueFileURL = null;
        try {
            // 업로드 경로 확인
            String uniqueFileName = UUID.randomUUID().toString() + "-" + picture.getOriginalFilename();
            uniqueFileURL = UPLOAD_FOLDER + PROFILE_FOLDER + uniqueFileName;
            Path path = Paths.get(uniqueFileURL);

            // 디렉토리 없으면 만들기
            Files.createDirectories(path.getParent());
            // 파일 저장
            picture.transferTo(path);
            
        } catch (Exception e) {
            return null;
        }
        return uniqueFileURL;
    }

    public String uploadNoticeFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            // 업로드 경로 확인 및 폴더 생성
            String uniqueFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            String uniqueFileURL = UPLOAD_FOLDER + NOTICE_FOLDER + uniqueFileName;
            Path path = Paths.get(uniqueFileURL);

            Files.createDirectories(path.getParent());
            // 파일 저장
            file.transferTo(path);

            return uniqueFileURL;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<?> downloadNoticeFile(String uniqueFileName, String realFileName) {
        try {
            Path filepath = Paths.get(uniqueFileName);
            File file = filepath.toFile();

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent = Files.readAllBytes(filepath);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + realFileName);
            return ResponseEntity.ok().headers(headers).body(fileContent);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("파일 입출력 에러");
        }
    }

}
