package com.gwantong.project.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.service.HotplaceService;

@RestController
@RequestMapping("/hotplace")
public class AttractionController {
    @Autowired
    HotplaceService hotplacesService;

    @GetMapping("/")
    public ResponseEntity<?> viewAllHotplaces() {
        List<HotplaceDto> list = hotplacesService.viewAllHotplaces();
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
