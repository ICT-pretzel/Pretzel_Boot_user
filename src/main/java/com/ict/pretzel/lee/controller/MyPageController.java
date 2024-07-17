package com.ict.pretzel.lee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ict.pretzel.lee.service.MyPageService;
import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.ProfileVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.WishVO;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @GetMapping("/userdetail")
    public ResponseEntity<?> userdetail(@RequestHeader("Authorization") String token) {
        JwtDecode jwtDecode = new JwtDecode(token);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.userdetail(userId));
    }

    @PostMapping("/watchlist")
    public ResponseEntity<List<MovieVO>> watchlist(@RequestBody ProfileVO profile) {
        int profileIdx = Integer.parseInt(profile.getProfile_idx());
        return ResponseEntity.ok(myPageService.watchlist(profileIdx));
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<MovieVO>> wishlist(@RequestBody ProfileVO profile) {
        int profileIdx = Integer.parseInt(profile.getProfile_idx());
        return ResponseEntity.ok(myPageService.wishlist(profileIdx));
    }

    @GetMapping("/questionlist")
    public ResponseEntity<List<QuestionVO>> questionlist(@RequestBody ProfileVO profile) {
        int profileIdx = Integer.parseInt(profile.getProfile_idx());
        return ResponseEntity.ok(myPageService.questionlist(profileIdx));
    }

    @GetMapping("/reviewlist")
    public ResponseEntity<List<ReviewVO>> reviewlist(@RequestBody ProfileVO profile) {
        int profileIdx = Integer.parseInt(profile.getProfile_idx());
        return ResponseEntity.ok(myPageService.reviewlist(profileIdx));
    }


    @PostMapping("/pay")
    public ResponseEntity<Integer> pay(@RequestHeader("Authorization") String token, @RequestParam String u_subs) {
        JwtDecode jwtDecode = new JwtDecode(token);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.pay(userId, u_subs));
    }
}
