package com.gwantong.project.list.service;

import java.util.List;

import com.gwantong.project.list.dto.AgeListDto;
import com.gwantong.project.list.dto.ContentTypeListDto;
import com.gwantong.project.list.dto.GugunListDto;
import com.gwantong.project.list.dto.SidoListDto;

public interface ListService {
    List<SidoListDto> getSidoList();

    List<GugunListDto> getGugunList();

    List<ContentTypeListDto> getContentTypeList();

    List<AgeListDto> getAgeList();
}
