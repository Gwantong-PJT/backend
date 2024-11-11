package com.gwantong.project.attraction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwantong.project.attraction.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {
    @Autowired
    AttractionMapper attractionMapper;

}
