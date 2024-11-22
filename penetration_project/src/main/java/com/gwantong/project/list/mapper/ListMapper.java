package com.gwantong.project.list.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.list.dto.AgeListDto;
import com.gwantong.project.list.dto.ContentTypeListDto;
import com.gwantong.project.list.dto.GugunListDto;
import com.gwantong.project.list.dto.SidoListDto;

@Mapper
public interface ListMapper {
    List<SidoListDto> getSidoList();

    List<GugunListDto> getGugunList();

    List<ContentTypeListDto> getContentTypeList();

    List<AgeListDto> getAgeList();
}
