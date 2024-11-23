package com.gwantong.project.attraction.service;

import java.util.List;

import com.gwantong.project.attraction.dto.AttractionDto;
import com.gwantong.project.user.dto.UserDto;

public interface AttractionService {
    List<AttractionDto> viewAll(AttractionDto attraction, int pageNum);

    List<AttractionDto> searchByUserCondition(UserDto userDto);

    List<AttractionDto> searchByAttractionCondition(AttractionDto attraction , int userNo);

    int pushLikeyButton(int userNo, int attractionNo);
}
