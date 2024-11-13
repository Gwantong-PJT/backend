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

    @Override
    public List<AttractionDto> viewAll(AttractionDto attraction) {
        return attractionMapper.viewAll(attraction);
    }
}
