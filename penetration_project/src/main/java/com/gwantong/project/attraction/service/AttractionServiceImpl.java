package com.gwantong.project.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwantong.project.attraction.dto.AttractionDto;
import com.gwantong.project.attraction.mapper.AttractionMapper;
import com.gwantong.project.user.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public List<AttractionDto> searchByUserCondition(UserDto userDto) {
        List<AttractionDto> list = attractionMapper.searchByUserCondition(userDto);
        log.info(list.get(0).toString());
        return list;

    }

    @Override
    public List<AttractionDto> searchByAttractionCondition(AttractionDto attraction) {
        return attractionMapper.searchByAttractionCondition(attraction);
    }

}
