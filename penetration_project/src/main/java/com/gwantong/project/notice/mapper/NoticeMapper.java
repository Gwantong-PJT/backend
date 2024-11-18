package com.gwantong.project.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gwantong.project.notice.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
    List<NoticeDto> viewAllNotices();

    NoticeDto viewNotice(int noticeNo);
}
