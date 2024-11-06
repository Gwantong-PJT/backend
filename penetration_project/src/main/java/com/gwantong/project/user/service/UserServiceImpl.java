package com.gwantong.project.user.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwantong.project.user.dto.UserDto;
import com.gwantong.project.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public UserDto loginUser(UserDto user) {
        return userMapper.loginUser(user);
    }

    @Override
    public int signUpUser(UserDto user) {
        int result = 0;

        // 중복된 ID를 등록하려 하면 예외 발생 => 처리
        try {
            result = userMapper.signUpUser(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            result = -1;
        } catch (Exception e) {
            result = -2;
        }

        return result;
    }

}
