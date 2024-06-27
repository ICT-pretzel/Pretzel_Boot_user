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
    
    @PostMapping("/confirm")
    public ResponseEntity<TossVO> tossConfirm(@RequestBody TossVO toss, 
                    @RequestHeader("Authorization") String token) {
        try {
            // 토큰에서 user_id 빼내기
            JwtDecode jwtDecode = new JwtDecode(token);

            // 결제 승인 처리 후 결제 정보 받기
            TossVO result = tossService.confirmPayment(jwtDecode.getUser_id(),
                toss.getPaymentKey(), toss.getOrderId(), toss.getAmount()
                );
            
            if (result != null) {
                System.out.println("결제 성공");
                return ResponseEntity.ok(result);
            } else {
                System.out.println("결제 실패");
                return ResponseEntity.status(400).body(null);
            }
        } catch (Exception e) {
            System.out.println("tossConfirm : " + e);
        }
        return null;
    }
}
