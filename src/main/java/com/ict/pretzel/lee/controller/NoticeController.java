package com.ict.pretzel.lee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ict.pretzel.lee.service.NoticeService;
import com.ict.pretzel.jwt.JwtDecode;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice_list")
    public ResponseEntity<?> notice_list() {
        return ResponseEntity.ok(noticeService.notice_list());
    }

    @GetMapping("/faq_list")
    public ResponseEntity<?> faq_list(@RequestParam("type") String type) {
        return ResponseEntity.ok(noticeService.faq_list(type));
    }

    @PostMapping("/question_add")
    public ResponseEntity<?> question_add(@RequestBody Map<String, String> requestData) {
        int profileIdx = Integer.parseInt(requestData.get("profile_idx"));
        String questionTitle = requestData.get("question_title");
        String questionContent = requestData.get("question_content");
        return ResponseEntity.ok(noticeService.question_add(profileIdx, questionTitle, questionContent));
    }
}
