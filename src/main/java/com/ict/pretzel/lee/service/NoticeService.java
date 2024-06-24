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

    public ResponseEntity<List<NoticeVO>> list() {
        List<NoticeVO> noticeList = noticeMapper.list();
        return ResponseEntity.ok(noticeList);
    }

    public ResponseEntity<List<FaqVO>> faq() {
        List<FaqVO> faqList = noticeMapper.faq();
        return ResponseEntity.ok(faqList);
    }

    public ResponseEntity<Integer> add(int profileIdx, String question_title, String question_content) { 
        int result = noticeMapper.add(profileIdx, question_title, question_content);
        return ResponseEntity.ok(result);
    }
}
