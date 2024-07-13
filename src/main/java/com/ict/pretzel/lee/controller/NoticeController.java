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

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(noticeService.list());
    }

    @GetMapping("/faq")
    public ResponseEntity<?> faq() {
        return ResponseEntity.ok(noticeService.faq());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Map<String, String> requestData) {
        int profileIdx = Integer.parseInt(requestData.get("profile_idx"));
        String questionTitle = requestData.get("question_title");
        String questionContent = requestData.get("question_content");
        return ResponseEntity.ok(noticeService.add(profileIdx, questionTitle, questionContent));
    }
}
