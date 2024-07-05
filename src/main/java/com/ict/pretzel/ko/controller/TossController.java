package com.ict.pretzel.ko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.ko.service.TossService;
import com.ict.pretzel.ko.vo.TossVO;

@RestController
@RequestMapping("/toss")
public class TossController {

    @Autowired
    private TossService tossService;
    
    // 토스 결제 승인
    @PostMapping("/confirm")
    public ResponseEntity<?> tossConfirm(@RequestHeader("Authorization") String token, 
                                        @RequestBody TossVO toss) {
            // 토큰에서 user_id 빼내기
            JwtDecode jwtDecode = new JwtDecode(token);
            toss.setUser_id(jwtDecode.getUser_id());

            return tossService.tossConfirm(toss);
    }

}
