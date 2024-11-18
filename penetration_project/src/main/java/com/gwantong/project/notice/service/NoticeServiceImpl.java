package com.gwantong.project.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwantong.project.notice.dto.NoticeDto;
import com.gwantong.project.notice.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<NoticeDto> viewAllNotices() {
        return noticeMapper.viewAllNotices();
    }

    @Override
    public NoticeDto viewNotice(int noticeNo) {
        return noticeMapper.viewNotice(noticeNo);
    }
}
