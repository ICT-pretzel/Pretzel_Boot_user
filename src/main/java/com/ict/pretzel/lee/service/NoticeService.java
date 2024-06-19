package com.ict.pretzel.lee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.pretzel.lee.mapper.NoticeMapper;
import com.ict.pretzel.vo.FaqVO;
import com.ict.pretzel.vo.NoticeVO;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<NoticeVO> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    public List<FaqVO> getFaqList() {
        return noticeMapper.getFaqList();
    }

    public int addQuestion(String token, String question_title, String question_content) {
        return noticeMapper.addQuestion(token, question_title, question_content);
    }
}
