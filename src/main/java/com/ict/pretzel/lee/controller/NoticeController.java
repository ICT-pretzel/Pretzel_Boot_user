package com.ict.pretzel.lee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ict.pretzel.lee.service.NoticeService;
import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.vo.FaqVO;
import com.ict.pretzel.vo.NoticeVO;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public ResponseEntity<?> getNoticeList() {
        return ResponseEntity.ok(noticeService.getNoticeList());
    }

    @GetMapping("/faq")
    public ResponseEntity<?> getFaqList() {
        return ResponseEntity.ok(noticeService.getFaqList());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestHeader("Authorization") String token, @RequestParam String question_title, @RequestParam String question_content) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        int profileIdx = Integer.parseInt(jwtDecode.getProfile_idx());
        return ResponseEntity.ok(noticeService.addQuestion(profileIdx, question_title, question_content));
    }
}
