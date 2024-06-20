package com.ict.pretzel.lee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.lee.service.NoticeService;
import com.ict.pretzel.vo.FaqVO;
import com.ict.pretzel.vo.NoticeVO;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public List<NoticeVO> getNoticeList() {
        return noticeService.getNoticeList();
    }

    @GetMapping("/faq")
    public List<FaqVO> getFaqList() {
        return noticeService.getFaqList();
    }

    @PostMapping("/add")
    public int addQuestion(@RequestParam int profileIdx, @RequestParam String question_title, @RequestParam String question_content) {
        return noticeService.addQuestion(profileIdx, question_title, question_content);
    }
}
/*
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public List<NoticeVO> getNoticeList(@RequestParam String token) {
        return noticeService.getNoticeList(token);
    }

    @GetMapping("/faq")
    public List<FaqVO> getFaqList(@RequestParam String token) {
        return noticeService.getFaqList(token);
    }

    @PostMapping("/add")
    public int addQuestion(@RequestParam String token, @RequestParam String question_title, @RequestParam String question_content) {
        return noticeService.addQuestion(token, question_title, question_content);
    }
}
*/