package com.gwantong.project.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private int userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userRole;
    private String userProFile;
    private int ageNo;
    private int userResidence;
}
