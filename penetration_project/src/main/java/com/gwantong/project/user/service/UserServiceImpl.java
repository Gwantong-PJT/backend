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
    public UserDto getUserInfoByUserId(String userId) {
        return userMapper.getUserInfoByUserId(userId);
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

    @Override
    public int updateUser(UserDto user) {
        int result = 0;

        result = userMapper.updateUser(user);

        return result;
    }

    @Override
    public String findUserPassword(UserDto user) {
        String findPw = userMapper.findUserPassword(user);

        // 없으면 null 반환
        if (findPw == null || findPw.equals("")) {
            return null;
        }

        // 검색 성공 시 * 처리
        if (findPw.length() == 1) {
            return findPw;
        }

        StringBuilder sb = new StringBuilder();

        if (findPw.length() <= 7) {
            String tmp = findPw.substring(0, findPw.length() / 2);
            sb.append(tmp).append("*".repeat(findPw.length() - tmp.length()));
        } else {
            String tmp = findPw.substring(0, 4);
            sb.append(tmp).append("*".repeat(findPw.length() - tmp.length()));
        }

        return sb.toString();
    }

}
