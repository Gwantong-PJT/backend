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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "유저", description = "회원 목록, 상세 보기, 등록, 수정, 로그인 등 회원 정보 관련 클래스(users_tb)")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthorizationService authorizationService;

    @Operation(summary = "로그인", description = "userID와 userPassword로 로그인을 진행한다.<br>나머지는 입력할 필요 없음")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @Parameter(required = true, description = "로그인 할 사용자 ID") @RequestBody UserDto requestUser) {
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

    @Operation(summary = "회원 목록 전체 조회", description = "전체 회원 목록을 조회, 필요 입력 없음<br>로그인 후 진행")
    @GetMapping("/all")
    public ResponseEntity<?> selectAllUser() {
        List<UserDto> userlist = userService.selectAllUser();
        return ResponseEntity.ok(userlist);
    }

    @Operation(summary = "회원 한명 조회", description = "userID에 맞는 사용자 정보를 조회한다.<br>로그인 후 진행할 것<br>현재 로그인만 되어있으면 다른 사용자 정보도 조회 가능함")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable("userId") String userId) {
        UserDto loginUser = userService.getUserInfoByUserId(userId);
        if (loginUser != null) {
            return ResponseEntity.ok(loginUser);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "회원 가입", description = "정보를 입력하여 회원 가입을 진행<br>userId와 userPassword는 필수<br>userName은 선택<br>나머지는 현재 미구현이라 입력 필요 x")
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

    @Operation(summary = "회원 정보 수정", description = "userId를 기반으로 회원의 정보를 수정<br>userId는 필수<br>나머지는 선택 (ageValue는 다른 테이블이라 적용 안됨)<br>로그인 후 진행")
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
