package com.gwantong.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwantong.project.dto.UserDto;
import com.gwantong.project.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public UserDto loginUser(String userId, String userPw) {
        return userMapper.loginUser(userId, userPw);
    }

}
