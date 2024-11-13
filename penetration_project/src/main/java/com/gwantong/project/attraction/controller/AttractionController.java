package com.gwantong.project.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.attraction.dto.AttractionDto;
import com.gwantong.project.attraction.service.AttractionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/attraction")
@Tag(name = "관광지", description = "관광지 조회 (attractions)")
public class AttractionController {
    @Autowired
    AttractionService attractionService;

    @Operation(summary = "관광지 전체보기", description = "상위 관광지 100개를 조회한다.")
    @GetMapping("/")
    public ResponseEntity<?> viewAll() {
        List<AttractionDto> list = attractionService.viewAll();
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
