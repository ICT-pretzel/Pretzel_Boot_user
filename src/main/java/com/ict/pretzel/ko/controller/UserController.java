package com.ict.pretzel.ko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.ko.service.AuthService;
import com.ict.pretzel.ko.service.UserService;
import com.ict.pretzel.vo.UserVO;


@RestController
@RequestMapping("/user")
public class UserController {
    
    // 인증
    @Autowired
    private AuthService authService;
    
    // 유저 서비스
    @Autowired
    private UserService userService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserVO user) {
        return authService.login(user);
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserVO user) {
        return userService.join(user);
    }
    
    
}
