package com.gwantong.project.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwantong.project.attraction.dto.AttractionDto;
import com.gwantong.project.attraction.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {
    @Autowired
    AttractionMapper attractionMapper;

    final int PAGING = 100;

    @Override
    public List<AttractionDto> viewAll(AttractionDto attraction, int pageNum) {
        int starting = (pageNum - 1) * PAGING;
        return attractionMapper.viewAll(attraction, starting, PAGING);
    }

    @Override
    public List<AttractionDto> searchByAgeRanking(int ageNo) {
        List<AttractionDto> list = attractionMapper.searchByAgeRanking(ageNo);
        return list;
    }

}
