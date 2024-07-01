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
    public ResponseEntity<?> tossConfirm(@RequestBody TossVO toss, 
                    @RequestHeader("Authorization") String token) {
        
            // 토큰에서 user_id 빼내기
            JwtDecode jwtDecode = new JwtDecode(token);
            toss.setUser_id(jwtDecode.getUser_id());

            // 결제 승인 처리 후 결제 정보 받기
            TossVO result = tossService.tossConfirm(toss);
            
            if (result != null) {
                System.out.println("결제 성공");
                return ResponseEntity.ok(result);
            } else {
                System.out.println("결제 실패");
                return ResponseEntity.status(400).body(null);
            }
    }

    // 토스 결제 취소
    @PostMapping("/cancel")
    public ResponseEntity<?> tossCancel(@RequestBody TossVO toss) {
        return tossService.tossCancel(toss.getToss_idx(), toss.getCancelReason());
    }
    



}
