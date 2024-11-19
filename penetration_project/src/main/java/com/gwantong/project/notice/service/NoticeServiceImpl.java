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

    @Override
    public int insertNotice(NoticeDto notice) {
        try {
            noticeMapper.insertNotice(notice);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int updateNotice(NoticeDto notice) {
        try {
            noticeMapper.updateNotice(notice);
        } catch (Exception e) {
            return 0;
        }
        return 1;

    }

    @Override
    public int deleteNotice(int noticeNo) {
        try {
            noticeMapper.deleteNotice(noticeNo);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
