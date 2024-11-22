package com.gwantong.project.list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwantong.project.list.dto.AgeListDto;
import com.gwantong.project.list.dto.ContentTypeListDto;
import com.gwantong.project.list.dto.GugunListDto;
import com.gwantong.project.list.dto.SidoListDto;
import com.gwantong.project.list.mapper.ListMapper;

@Service
public class ListServiceImpl implements ListService {
    @Autowired
    ListMapper listMapper;

    @Override
    public List<SidoListDto> getSidoList() {
        return listMapper.getSidoList();
    }

    @Override
    public List<GugunListDto> getGugunList() {
        return listMapper.getGugunList();
    }

    @Override
    public List<ContentTypeListDto> getContentTypeList() {
        return listMapper.getContentTypeList();
    }

    @Override
    public List<AgeListDto> getAgeList() {
        return listMapper.getAgeList();
    }

}
