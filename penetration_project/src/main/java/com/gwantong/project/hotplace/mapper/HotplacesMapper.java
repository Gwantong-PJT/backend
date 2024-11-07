package com.gwantong.project.hotplace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.hotplace.dto.HotplaceDto;

@Mapper
public interface HotplacesMapper {
    List<HotplaceDto> viewAllHotplaces();

    void increaseHotplaceViews(int hotplaceNo);

    HotplaceDto selectHotplace(int hotplaceNo);
}
