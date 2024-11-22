package com.gwantong.project.hotplace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.hotplace.dto.CommentDto;
import com.gwantong.project.hotplace.dto.HotplaceDto;
import com.gwantong.project.hotplace.dto.HotplacePictureDto;

@Mapper
public interface HotplaceMapper {
    List<HotplaceDto> viewAllHotplaces();

    List<HotplaceDto> usersHotplaces(int userNo);

    void increaseHotplaceViews(int hotplaceNo);

    HotplaceDto selectHotplace(int hotplaceNo);

    int insertHotplace(HotplaceDto hotplaceDto) throws Exception;

    int updateHotplace(HotplaceDto hotplaceDto) throws Exception;

    int deleteHotplace(int hotplaceNo) throws Exception;

    // 댓글 CUD
    int insertComment(CommentDto commentDto) throws Exception;

    int updateComment(CommentDto commentDto) throws Exception;

    int deleteComment(int commentDto) throws Exception;

    // 사진 업로드
    int uploadPicture(List<HotplacePictureDto> list);

}
