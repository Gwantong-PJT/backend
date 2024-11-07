package com.gwantong.project.hotplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.service.HotplacesService;

@RestController
@RequestMapping("/hotplace")
public class HotplacesController {
    @Autowired
    HotplacesService hotplacesService;

    @GetMapping("/")
    public ResponseEntity<?> viewAllHotplaces() {
        List<HotplaceDto> list = hotplacesService.viewAllHotplaces();
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{hotpl_no}")
    public ResponseEntity<?> selectHotplace(@PathVariable("hotpl_no") int hotpl_no) {
        HotplaceDto hotpl = hotplacesService.selectHotplace(hotpl_no);
        if (hotpl != null) {
            return ResponseEntity.ok(hotpl);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
