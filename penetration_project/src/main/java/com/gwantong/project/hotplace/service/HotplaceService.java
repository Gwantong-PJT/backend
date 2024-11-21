package com.gwantong.project.hotplace.service;

import java.util.List;

import com.gwantong.project.hotplace.dto.CommentDto;
import com.gwantong.project.hotplace.dto.HotplaceDto;

public interface HotplaceService {
    List<HotplaceDto> viewAllHotplaces();

    List<HotplaceDto> usersHotplaces(int userNo);

    HotplaceDto selectHotplace(int hotplaceNo);

    int insertHotplace(HotplaceDto hotplaceDto);

    int updateHotplace(HotplaceDto hotplaceDto);

    int deleteHotplace(int hotplaceNo);

    // 댓글
    int insertComment(CommentDto commentDto);

    int updateComment(CommentDto commentDto);

    int deleteComment(int commentDto);
}
