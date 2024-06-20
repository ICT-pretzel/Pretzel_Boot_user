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

    public int addQuestion(int profileIdx, String question_title, String question_content) {
        return noticeMapper.addQuestion(profileIdx, question_title, question_content);
    }
}
/*
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private JWTUtil jwtUtil;

    public List<NoticeVO> getNoticeList(String token) {
        int profileIdx = Integer.parseInt(jwtUtil.extractProfileIdx(token));
        return noticeMapper.getNoticeList(profileIdx);
    }

    public List<FaqVO> getFaqList(String token) {
        int profileIdx = Integer.parseInt(jwtUtil.extractProfileIdx(token));
        return noticeMapper.getFaqList(profileIdx);
    }

    public int addQuestion(String token, String questionTitle, String questionContent) {
        int profileIdx = Integer.parseInt(jwtUtil.extractProfileIdx(token));
        return noticeMapper.addQuestion(profileIdx, questionTitle, questionContent);
    }
}
*/