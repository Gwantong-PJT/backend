package com.gwantong.project.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.attraction.dto.AttractionDto;
import com.gwantong.project.attraction.service.AttractionService;

@RestController
@RequestMapping("/attraction")
public class AttractionController {
    @Autowired
    AttractionService attractionService;

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
