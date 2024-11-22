package com.gwantong.project.attraction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.attraction.dto.AttractionDto;
import com.gwantong.project.user.dto.UserDto;

@Mapper
public interface AttractionMapper {
    List<AttractionDto> viewAll(AttractionDto attraction, int pageNum, int pageLen);

    List<AttractionDto> searchByUserCondition(UserDto userDto);

    List<AttractionDto> searchByAttractionCondition(AttractionDto attraction);

}
