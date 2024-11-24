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
    public List<AttractionDto> searchByAttractionCondition(AttractionDto attraction , int userNo) {
        return attractionMapper.searchByAttractionCondition2(attraction, userNo);
    }

    @Override
    public int pushLikeyButton(int userNo, int attractionNo) {
        return attractionMapper.pushLikeyButton(userNo, attractionNo);
    }

    @Override
    public List<AttractionDto> viewLikeyAttractions(int userNo) {
        return attractionMapper.viewLikeyAttractions(userNo);
    }

    @Override
    public List<AttractionDto> getCircularChart(UserDto user) {
        List<AttractionDto> list = attractionMapper.getCircularChart(user);
        double viewSum = 0;
        for(AttractionDto att : list){
            viewSum += att.getViews();
        }
        
        for(AttractionDto att : list){
            att.setViewPersent(Math.round(att.getViews() / viewSum * 100.0) / 100.0);
        }
        
        for(AttractionDto att : list){
            log.info(att.getViewPersent() + "");}

        return list;
    }

}
