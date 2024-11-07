package com.gwantong.project.hotplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.mapper.HotplacesMapper;

@Service
public class HotplacesServiceImpl implements HotplacesService {
    @Autowired
    HotplacesMapper hotplacesMapper;

    @Override
    public List<HotplaceDto> viewAllHotplaces() {
        return hotplacesMapper.viewAllHotplaces();
    }

    @Override
    @Transactional
    public HotplaceDto selectHotplace(int hotplaceNo) {
        hotplacesMapper.increaseHotplaceViews(hotplaceNo);
        return hotplacesMapper.selectHotplace(hotplaceNo);
    }

}
