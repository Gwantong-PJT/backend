package com.gwantong.project.attraction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.attraction.dto.AttractionDto;

@Mapper
public interface AttractionMapper {
    List<AttractionDto> viewAll(AttractionDto attraction, int pageNum, int pageLen);

    List<AttractionDto> searchByAgeRanking(int ageNo);
}
