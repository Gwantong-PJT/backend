package com.gwantong.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwantong.project.dto.UserDto;
import com.gwantong.project.service.UserService;

@RestController
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> selectAllUser() {
        List<UserDto> userlist = userService.selectAllUser();
        return ResponseEntity.ok(userlist);
    }

    @GetMapping("/{userid}/{userpw}")
    public ResponseEntity<?> loginUser(@PathVariable("userid") String userId, @PathVariable("userpw") String userPw) {
        UserDto user = userService.loginUser(userId, userPw);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> postMethodName() {
        return ResponseEntity.ok(null);
    }

}
