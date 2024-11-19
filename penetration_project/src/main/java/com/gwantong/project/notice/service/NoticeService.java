package com.gwantong.project.notice.service;

import java.util.List;

import com.gwantong.project.notice.dto.NoticeDto;

public interface NoticeService {
    List<NoticeDto> viewAllNotices();

    NoticeDto viewNotice(int noticeNo);

    int insertNotice(NoticeDto notice);

    int updateNotice(NoticeDto notice);

    int deleteNotice(int noticeNo);
}
