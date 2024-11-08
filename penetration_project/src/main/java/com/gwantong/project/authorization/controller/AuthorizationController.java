package com.gwantong.project.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.authorization.service.AuthorizationService;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

    @GetMapping({ "/", "" })
    public ResponseEntity<?> getAuthorization(@RequestHeader("Jwt-Refresh") String refresh,
            @RequestHeader("userId") String userId) {

        // 여기 해야 함
        return ResponseEntity.ok(null);
    }
}
