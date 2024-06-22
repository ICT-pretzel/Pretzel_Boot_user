package com.ict.pretzel.lee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // 추가
import org.springframework.stereotype.Service;
import com.ict.pretzel.lee.mapper.NoticeMapper;
import com.ict.pretzel.vo.FaqVO;
import com.ict.pretzel.vo.NoticeVO;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public ResponseEntity<List<NoticeVO>> getNoticeList() {
        List<NoticeVO> noticeList = noticeMapper.getNoticeList();
        return ResponseEntity.ok(noticeList);
    }

    public ResponseEntity<List<FaqVO>> getFaqList() {
        List<FaqVO> faqList = noticeMapper.getFaqList();
        return ResponseEntity.ok(faqList);
    }

    public ResponseEntity<Integer> addQuestion(int profileIdx, String question_title, String question_content) { // 수정
        int result = noticeMapper.addQuestion(profileIdx, question_title, question_content);
        return ResponseEntity.ok(result);
    }
}
