package com.gwantong.project.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.user.dto.UserDto;

@Mapper
public interface UserMapper {
    List<UserDto> selectAllUser();

    UserDto loginUser(UserDto user);

    UserDto getUserInfoByUserId(String userId);

    int signUpUser(UserDto user) throws Exception;

    int updateUser(UserDto user);

    String findUserPassword(UserDto user);

}
