package com.gwantong.project.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.user.dto.UserDto;

@Mapper
public interface UserMapper {
    List<UserDto> selectAllUser();

    UserDto loginUser(String userId, String userPw);

    int signUpUser(UserDto user);
}
