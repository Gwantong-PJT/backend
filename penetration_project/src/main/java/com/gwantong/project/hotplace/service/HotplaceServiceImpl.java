package com.gwantong.project.hotplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwantong.project.hotplace.dto.CommentDto;
import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.dto.HotplacePictureDto;
import com.gwantong.project.hotplace.mapper.HotplaceMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotplaceServiceImpl implements HotplaceService {
    @Autowired
    HotplaceMapper hotplacesMapper;

    @Override
    public List<HotplaceDto> viewAllHotplaces() {
        return hotplacesMapper.viewAllHotplaces();
    }

    @Override
    @Transactional
    public HotplaceDto selectHotplace(int hotplaceNo) {
        hotplacesMapper.increaseHotplaceViews(hotplaceNo);
        log.info("조회수 추가는 완료 됨");
        HotplaceDto hotpl = hotplacesMapper.selectHotplace(hotplaceNo);
        log.info("데이터 조회도 완료 됨");
        return hotpl;
    }

    @Override
    public int insertHotplace(HotplaceDto hotplaceDto) {
        int result;
        try {
            result = hotplacesMapper.insertHotplace(hotplaceDto);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public int updateHotplace(HotplaceDto hotplaceDto) {
        int result;
        try {
            result = hotplacesMapper.updateHotplace(hotplaceDto);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public int deleteHotplace(int hotplaceNo) {
        int result;
        try {
            result = hotplacesMapper.deleteHotplace(hotplaceNo);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public int insertComment(CommentDto commentDto) {
        int result;
        try {
            result = hotplacesMapper.insertComment(commentDto);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public int updateComment(CommentDto commentDto) {
        int result;
        try {
            result = hotplacesMapper.updateComment(commentDto);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public int deleteComment(int commentDto) {
        int result;
        try {
            result = hotplacesMapper.deleteComment(commentDto);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public List<HotplaceDto> usersHotplaces(int userNo) {
        return hotplacesMapper.usersHotplaces(userNo);
    }

    @Override
    public int uploadPicture(List<HotplacePictureDto> list) {
        return hotplacesMapper.uploadPicture(list);
    }
}
