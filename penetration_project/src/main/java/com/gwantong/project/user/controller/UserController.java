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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "유저 컨트롤러", description = "회원 목록, 상세 보기, 등록, 수정, 로그인 등 회원 정보 관련 클래스(users_tb)")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthorizationService authorizationService;

    @Operation(summary = "로그인", description = "ID와 PW로 로그인을 진행한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/")
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

    @Operation(summary = "회원 목록 전체 보기", description = "전체 회원 목록을 조회, 로그인 후 진행")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
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
