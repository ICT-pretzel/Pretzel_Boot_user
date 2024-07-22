package com.ict.pretzel.lee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Service;
import com.ict.pretzel.lee.mapper.NoticeMapper;
import com.ict.pretzel.vo.FaqVO;
import com.ict.pretzel.vo.NoticeVO;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public ResponseEntity<List<NoticeVO>> notice_list() {
        List<NoticeVO> noticeList = noticeMapper.notice_list();
        return ResponseEntity.ok(noticeList);
    }

    public ResponseEntity<List<FaqVO>> faq_list(String type) {
        List<FaqVO> faqList = noticeMapper.faq_list(type);
        return ResponseEntity.ok(faqList);
    }

    public ResponseEntity<Integer> question_add(int profileIdx, String question_title, String question_content) { 
        int result = noticeMapper.question_add(profileIdx, question_title, question_content);
        return ResponseEntity.ok(result);
    }
}
