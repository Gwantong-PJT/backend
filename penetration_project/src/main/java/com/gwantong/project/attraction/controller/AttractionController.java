package com.gwantong.project.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.attraction.dto.AttractionDto;
import com.gwantong.project.attraction.service.AttractionService;
import com.gwantong.project.user.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/attraction")
@Tag(name = "관광지", description = "관광지 조회 (attractions)")
public class AttractionController {
    @Autowired
    AttractionService attractionService;

    @Operation(summary = "관광지 100개 검색", description = "상위 관광지 100개를 조회한다.<br>그냥 검색할 수도 있고, 아래 항목을 넣어서 검색할 수도 있다<br> - 카테고리(contentTypeId)<br> - 지역 번호(areaCode)")
    @GetMapping("/")
    public ResponseEntity<?> viewAll(@RequestBody AttractionDto attraction) {
        List<AttractionDto> list = attractionService.viewAll(attraction, 2);
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "조건으로 추천여행지 검색", description = "여러가지 조건에 맞추어 조회 수 순으로 높은 여행지 10곳을 반환<br>나이(ageNo), 지역(userRegion), 성별(userSex) 조건 검색 가능")
    @GetMapping("/search")
    public ResponseEntity<?> searchByCondition(@RequestParam(value = "userSex", defaultValue = "0") int userSex,
            @RequestParam(value = "userRegion", defaultValue = "0") int userRegion,
            @RequestParam(value = "ageNo", defaultValue = "0") int ageNo) {
        UserDto user = new UserDto();
        user.setAgeNo(ageNo);
        user.setUserRegion(userRegion);
        user.setUserSex(userSex);

        log.info("검색 메소드 실행 : " + user.toString());
        List<AttractionDto> list = attractionService.searchByCondition(user);
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
