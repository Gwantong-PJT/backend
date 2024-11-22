package com.gwantong.project.list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.list.dto.AgeListDto;
import com.gwantong.project.list.dto.ContentTypeListDto;
import com.gwantong.project.list.dto.GugunListDto;
import com.gwantong.project.list.dto.SidoListDto;
import com.gwantong.project.list.service.ListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/list")
@Tag(name = "리스트 유틸", description = "시도, 구군, 카테고리, 나이 등 단순 리스트가 필요한 것들")
public class ListController {
    @Autowired
    ListService listService;

    @Operation(summary = "시도 리스트", description = "sidoCode 사용하여 통신, sidoName으로 화면 표기<br>sidoNo는 무시할 것")
    @GetMapping("/sido")
    public ResponseEntity<?> getSidoList() {
        List<SidoListDto> list = listService.getSidoList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "구군 리스트", description = "gugunCode 사용하여 통신, gugunName으로 화면 표기<br>sidoCode로 소속 시도 확인<br>gugunNo는 무시할 것")
    @GetMapping("/gugun")
    public ResponseEntity<?> getGugunList() {
        List<GugunListDto> list = listService.getGugunList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "카테고리 리스트", description = "contentTypeId 사용하여 통신<br>contentTypeName으로 화면 표기")
    @GetMapping("/type")
    public ResponseEntity<?> getContentTypeList() {
        List<ContentTypeListDto> list = listService.getContentTypeList();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "나이 리스트", description = "ageNo 사용하여 통신<br>ageValue으로 화면 표기")
    @GetMapping("/age")
    public ResponseEntity<?> getAgeList() {
        List<AgeListDto> list = listService.getAgeList();
        return ResponseEntity.ok(list);
    }

}
