package com.gwantong.project.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.user.dto.UserDto;
import com.gwantong.project.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping({ "/", "" })
    public ResponseEntity<?> selectAllUser() {
        List<UserDto> userlist = userService.selectAllUser();
        return ResponseEntity.ok(userlist);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto requestUser) {
        UserDto loginUser = userService.loginUser(requestUser);
        if (loginUser != null) {
            return ResponseEntity.ok(loginUser);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserDto requestUser) {
        int result = userService.signUpUser(requestUser);
        if (result != 0) {
            return ResponseEntity.ok(result);
        } else {
            // 204 No content
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update")
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
