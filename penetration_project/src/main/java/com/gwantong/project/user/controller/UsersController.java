package com.gwantong.project.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/{userid}/{userpw}")
    public ResponseEntity<?> loginUser(@PathVariable("userid") String userId, @PathVariable("userpw") String userPw) {
        UserDto user = userService.loginUser(userId, userPw);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserDto user) {
        int result = userService.signUpUser(user);
        if (result != 0) {
            return ResponseEntity.ok(result);
        } else {
            // 204 No content
            return ResponseEntity.noContent().build();
        }
    }

}
