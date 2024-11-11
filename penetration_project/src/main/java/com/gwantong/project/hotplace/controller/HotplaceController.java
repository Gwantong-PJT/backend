package com.gwantong.project.hotplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.service.HotplaceService;

@RestController
@RequestMapping("/hotplace")
public class HotplaceController {
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

    @GetMapping("/{hotpl_no}")
    public ResponseEntity<?> selectHotplace(@PathVariable("hotpl_no") int hotpl_no) {
        HotplaceDto hotpl = hotplacesService.selectHotplace(hotpl_no);
        if (hotpl != null) {
            return ResponseEntity.ok(hotpl);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insertHotplace(@RequestBody HotplaceDto hotpl) {
        int result = hotplacesService.insertHotplace(hotpl);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateHotplace(@RequestBody HotplaceDto hotpl) {
        int result = hotplacesService.updateHotplace(hotpl);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteHotplace(@RequestBody int hotpl) {
        int result = hotplacesService.deleteHotplace(hotpl);
        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

}
