package com.gwantong.project.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.authorization.service.AuthorizationService;
import com.gwantong.project.user.dto.UserDto;
import com.gwantong.project.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthorizationService authorizationService;

    @GetMapping("/")
    public ResponseEntity<?> loginUser(@RequestBody UserDto requestUser) {
        UserDto loginUser = userService.loginUser(requestUser);
        if (loginUser != null) {
            Map<String, Object> responseObj = new HashMap<>();
            responseObj.put("userDto", loginUser);
            responseObj.put("jwt", authorizationService.generateFreshToken(loginUser.getUserId()));

            return ResponseEntity.ok(responseObj);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> selectAllUser() {
        List<UserDto> userlist = userService.selectAllUser();
        return ResponseEntity.ok(userlist);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable("userId") String userId) {
        UserDto loginUser = userService.getUserInfoByUserId(userId);
        if (loginUser != null) {
            return ResponseEntity.ok(loginUser);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> signUpUser(@RequestBody UserDto requestUser) {
        int result = userService.signUpUser(requestUser);
        if (result != 0) {
            return ResponseEntity.ok(result);
        } else {
            // 204 No content
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@RequestBody UserDto requestUser) {
        int result = userService.updateUser(requestUser);
        if (result != 0) {
            return ResponseEntity.ok(result);
        } else {
            // 204 No content
            return ResponseEntity.noContent().build();
        }
    }

}
