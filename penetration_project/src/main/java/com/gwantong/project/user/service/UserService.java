package com.gwantong.project.user.service;

import java.util.List;

import com.gwantong.project.user.dto.UserDto;

public interface UserService {
    List<UserDto> selectAllUser();

    UserDto loginUser(UserDto user);

    int signUpUser(UserDto user);

    int updateUser(UserDto user);
}
