package com.gwantong.project.user.service;

import java.util.List;

import com.gwantong.project.user.dto.UserDto;

public interface UserService {
    List<UserDto> selectAllUser();

    UserDto loginUser(String userId, String userPw);

    int signUpUser(UserDto user);
}
