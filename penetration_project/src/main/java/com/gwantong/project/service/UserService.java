package com.gwantong.project.service;

import java.util.List;

import com.gwantong.project.dto.UserDto;

public interface UserService {
    List<UserDto> selectAllUser();

    UserDto loginUser(String userId, String userPw);
}
