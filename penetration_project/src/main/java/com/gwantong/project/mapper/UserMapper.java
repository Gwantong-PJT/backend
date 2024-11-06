package com.gwantong.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.dto.UserDto;

@Mapper
public interface UserMapper {
    List<UserDto> selectAllUser();

    UserDto loginUser(String userId, String userPw);
}
