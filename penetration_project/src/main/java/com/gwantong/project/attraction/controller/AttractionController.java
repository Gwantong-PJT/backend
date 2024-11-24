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
import com.gwantong.project.user.service.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RestController
@RequestMapping("/attraction")
@Tag(name = "관광지", description = "관광지 조회 (attractions)")
public class AttractionController {
    @Autowired
    AttractionService attractionService;
    @Autowired
    UserService userService;

    // deprecated
    @Hidden
    @Operation(summary = "관광지 100개 검색", description = "번호순으로 관광지 100개를 조회한다.<br>그냥 검색할 수도 있고, 아래 항목을 넣어서 검색할 수도 있다<br> - 카테고리(contentTypeId)<br> - 지역 번호(areaCode)")
    @GetMapping("/aaaaaaaaaa")
    public ResponseEntity<?> viewAll(@RequestBody AttractionDto attraction) {
        List<AttractionDto> list = attractionService.viewAll(attraction, 2);
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "사용자에게 맞는 추천여행지 검색", description = "여러가지 조건에 맞추어 조회 수 순으로 높은 여행지 100곳을 반환<br>나이(ageNo), 지역(userRegion), 성별(userSex) 조건 검색 가능")
    @GetMapping("/user")
    public ResponseEntity<?> searchByCondition(@RequestParam(value = "userSex", defaultValue = "0") int userSex,
            @RequestParam(value = "userRegion", defaultValue = "0") int userRegion,
            @RequestParam(value = "ageNo", defaultValue = "0") int ageNo) {
        UserDto user = new UserDto();
        user.setAgeNo(ageNo);
        user.setUserRegion(userRegion);
        user.setUserSex(userSex);

        log.info("검색 메소드 실행 : " + user.toString());
        List<AttractionDto> list = attractionService.searchByUserCondition(user);
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "조건에 맞는 여행지 검색", description = "여러가지 조건에 맞추어 관광지 번호가 빠른 순으로 여행지 100곳을 반환<br>카테고리(contentTypeId), 지역(sidoCode), 검색어(keyWord) 조건 검색 가능<br>페이징은 추후에<br><b>좋아요 기능때문에 Authorize에 userId 꼭 넣을것</b>")
    @GetMapping("/")
    public ResponseEntity<?> searchByAttractionCondition(
            @RequestParam(value = "contentTypeId", defaultValue = "0") int contentTypeId,
            @RequestParam(value = "sidoCode", defaultValue = "0") int sidoCode,
            @RequestParam(value = "keyWord", required = false) String keyWord,
            HttpServletRequest request
            ) {
        AttractionDto attraction = new AttractionDto();
        attraction.setContentTypeId(contentTypeId);
        attraction.setAreaCode(sidoCode);
        attraction.setTitle(keyWord);

        String userId = (String) request.getAttribute("userId");
        int userNo = userService.getUserInfoByUserId(userId).getUserNo();

        log.info("100개 검색 메소드 실행 : " + attraction.toString() + ", 좋아요 유저 : " + userNo);
        List<AttractionDto> list = attractionService.searchByAttractionCondition(attraction, userNo);
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "좋아요 누르기", description = "attractionNo에 맞는 여행지를 좋아요 설정하기<br><b>Authorize에 userId 꼭 넣을것</b>")
    @PostMapping("/like")
    public ResponseEntity<?> pushLikeyButton(HttpServletRequest request, @RequestParam("attractionNo") int attractionNo) {
        String userId = (String) request.getAttribute("userId");
        int userNo = userService.getUserInfoByUserId(userId).getUserNo();

        int result = attractionService.pushLikeyButton(userNo, attractionNo);
        if(result == 1 || result == 2){
            return ResponseEntity.ok("success");
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "좋아요 설정한 여행지 보기", description = "사용자가 좋아요 누른 항목들만 보기<br><b>Authorize에 userId 꼭 넣을것</b>")
    @GetMapping("/like")
    public ResponseEntity<?> viewLikeyAttractions(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        int userNo = userService.getUserInfoByUserId(userId).getUserNo();

        List<AttractionDto> list = attractionService.viewLikeyAttractions(userNo);
        if(list != null){
            return ResponseEntity.ok(list);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "원형 차트 데이터 보기", description = "사용자의 나이와 성별이 비슷한 사람들이 많이 조회한 여행지를 추천")
    @GetMapping("/chart")
    public ResponseEntity<?> getCircularChart(@RequestParam(value = "ageNo", defaultValue = "0") int ageNo, 
    @RequestParam(value = "userSex", defaultValue = "0") int userSex) {
        UserDto user = new UserDto();
        user.setAgeNo(ageNo);
        user.setUserSex(userSex);

        List<AttractionDto> list = attractionService.getCircularChart(user);
        if(list != null){
            return ResponseEntity.ok(list);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

}
