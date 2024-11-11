package com.gwantong.project.attraction.service;

import java.util.List;

import com.gwantong.project.hotplace.dto.HotplaceDto;

public interface AttractionService {
    List<HotplaceDto> viewAllHotplaces();

    HotplaceDto selectHotplace(int hotplaceNo);

    int insertHotplace(HotplaceDto hotplaceDto);

    int updateHotplace(HotplaceDto hotplaceDto);

    int deleteHotplace(int hotplaceNo);
}
