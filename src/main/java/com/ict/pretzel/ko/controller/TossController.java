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
    public ResponseEntity<TossVO> confirmPayment(@RequestBody TossVO toss, 
                    @RequestHeader("Authorization") String token) {
        try {
            JwtDecode jwtDecode = new JwtDecode(token);
            TossVO isConfirmed = tossService.confirmPayment(jwtDecode.getUser_id(),
                toss.getPaymentKey(), toss.getOrderId(), toss.getAmount()
                );
            if (isConfirmed != null) {
                System.out.println("토스오나...2");
                return ResponseEntity.ok(isConfirmed);
            } else {
                System.out.println("토스오나...3");
                return ResponseEntity.status(400).body(null);
            }
        } catch (Exception e) {
            System.out.println("컨트롤러" + e );
        }
        return null;
    }
}
