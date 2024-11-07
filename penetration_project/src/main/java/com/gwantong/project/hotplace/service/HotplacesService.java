package com.gwantong.project.hotplace.service;

import java.util.List;

import com.gwantong.project.hotplace.dto.HotplaceDto;

public interface HotplacesService {
    List<HotplaceDto> viewAllHotplaces();

    HotplaceDto selectHotplace(int hotplaceNo);

    int updateHotplace(HotplaceDto hotplaceDto);
}
