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

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/list")
@Tag(name = "리스트 유틸", description = "시도, 구군, 카테고리, 나이 등 단순 리스트가 필요한 것들")
public class ListController {
    @Autowired
    ListService listService;

    @GetMapping("/sido")
    public ResponseEntity<?> getSidoList() {
        List<SidoListDto> list = listService.getSidoList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/gugun")
    public ResponseEntity<?> getGugunList() {
        List<GugunListDto> list = listService.getGugunList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/type")
    public ResponseEntity<?> getContentTypeList() {
        List<ContentTypeListDto> list = listService.getContentTypeList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/age")
    public ResponseEntity<?> getAgeList() {
        List<AgeListDto> list = listService.getAgeList();
        return ResponseEntity.ok(list);
    }

}
