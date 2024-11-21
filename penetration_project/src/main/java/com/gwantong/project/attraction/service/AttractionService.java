package com.gwantong.project.attraction.service;

import java.util.List;

import com.gwantong.project.attraction.dto.AttractionDto;

public interface AttractionService {
    List<AttractionDto> viewAll(AttractionDto attraction, int pageNum);

    List<AttractionDto> searchByAgeRanking(int ageNo);
}
