package com.gwantong.project.user.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gwantong.project.authorization.service.AuthorizationService;
import com.gwantong.project.hotplace.dto.HotplacePictureDto;
import com.gwantong.project.user.dto.UserDto;
import com.gwantong.project.user.service.UserService;
import com.gwantong.project.util.FileUpDownUtil;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "유저", description = "회원 목록, 상세 보기, 등록, 수정, 로그인 등 회원 정보 관련 클래스(users_tb)")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    FileUpDownUtil fileUpDownUtil;

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

    @Operation(summary = "비밀번호 찾기", description = "userId와 userName으로 해당 계정의 비밀번호를 검색한다.<br>비밀 번호는 앞자리 일부를 제외하고는 * 처리 되어 반환된다.")
    @PostMapping("/password")
    public ResponseEntity<?> findUserPassword(@RequestBody UserDto user) {
        String userPw = userService.findUserPassword(user);
        if (userPw != null) {
            return ResponseEntity.ok(userPw);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Hidden
    @Operation(summary = "회원 가입", description = "정보를 입력하여 회원 가입을 진행<br>userId와 userPassword는 필수<br>나머지는 선택, 필요 없다면 지울것<br>userNo, userRole은 자동으로 입력됨")
    @PostMapping("/signup/file")
    public ResponseEntity<?> signUpUser(@RequestBody UserDto requestUser) {
        int result = userService.signUpUser(requestUser);
        if (result != 0) {
            return ResponseEntity.ok(result);
        } else {
            // 204 No content
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "회원 가입 + 프로필 사진", description = "정보를 입력하여 회원 가입을 진행<br>userId와 userPassword는 필수<br>나머지는 선택, 필요 없다면 body에 담지 않아도 상관 없음<br>userNo, userRole은 자동으로 입력됨")
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUserwithProfile(
        @RequestParam("userId") String userId,
        @RequestParam("userPassword") String userPassword,
        @RequestParam(value = "userName", required = false) String userName,
        @RequestParam(value = "userProfile", required = false) MultipartFile userProfile,
        @RequestParam(value = "ageNo", defaultValue = "0") int ageNo,
        @RequestParam(value = "userRegion", defaultValue = "0") int userRegion,
        @RequestParam(value = "userSex", defaultValue = "0") int userSex
    ) {
        UserDto requestUser = new UserDto();
        requestUser.setUserId(userId);
        requestUser.setUserPassword(userPassword);
        requestUser.setUserName(userName);
        requestUser.setAgeNo(ageNo);
        requestUser.setUserRegion(userRegion);
        requestUser.setUserSex(userSex);

        //프로필 사진 세팅
        if (userProfile != null) {
            String profileUrl = fileUpDownUtil.uploadUserProfilePicture(userProfile);
            if (profileUrl == null) {
                return ResponseEntity.internalServerError().body("fail to upload pictures in system");
            }
            requestUser.setUserProfile(profileUrl);
        }

        log.info(requestUser.toString());
        
        //DB 처리 후 결과 반환
        int result = userService.signUpUser(requestUser);
        if (result != 1) {
            // 204 No content
            return ResponseEntity.badRequest().body("fail to sign in user");
        } 
        return ResponseEntity.ok(result);
    }
    
    @Operation(summary = "회원 정보 수정", description = "userId를 기반으로 회원의 정보를 수정<br>userId는 필수<br>나머지는 선택 (ageValue는 다른 테이블이라 적용 안됨)<br>로그인 후 진행")
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(
        @RequestParam("userId") String userId,
        @RequestParam(value = "userPassword", required = false) String userPassword,
        @RequestParam(value = "userName", required = false) String userName,
        @RequestParam(value = "userProfile", required = false) MultipartFile userProfile,
        @RequestParam(value = "ageNo", defaultValue = "0") int ageNo,
        @RequestParam(value = "userRegion", defaultValue = "0") int userRegion,
        @RequestParam(value = "userSex", defaultValue = "0") int userSex) {

    UserDto requestUser = new UserDto();
    requestUser.setUserId(userId);
    requestUser.setUserPassword(userPassword);
    requestUser.setUserName(userName);
    requestUser.setAgeNo(ageNo);
    requestUser.setUserRegion(userRegion);
    requestUser.setUserSex(userSex);

    //프로필 사진 세팅
    if (userProfile != null) {
        String profileUrl = fileUpDownUtil.uploadUserProfilePicture(userProfile);
        if (profileUrl == null) {
            return ResponseEntity.internalServerError().body("fail to upload pictures in system");
        }
        requestUser.setUserProfile(profileUrl);
    }

    log.info(requestUser.toString());
        int result = userService.updateUser(requestUser);
        if (result != 0) {
            return ResponseEntity.ok(result);
        } else {
            // 204 No content
            return ResponseEntity.noContent().build();
        }
    }

}
