package com.ict.pretzel.lee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ict.pretzel.lee.service.MyPageService;
import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.UserVO;
import com.ict.pretzel.vo.WishVO;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @GetMapping("/userdetail")
    public ResponseEntity<?> userdetail(@RequestHeader("Authorization") String token) {
        // "Bearer " 문자열 제거
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.userdetail(userId));
    }

    @GetMapping("/watchlist")
    public ResponseEntity<List<MovieVO>> watchlist(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        int profileIdx = Integer.parseInt(jwtDecode.getProfile_idx());
        return ResponseEntity.ok(myPageService.watchlist(profileIdx));
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<WishVO>> wishlist(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        int profileIdx = Integer.parseInt(jwtDecode.getProfile_idx());
        return ResponseEntity.ok(myPageService.wishlist(profileIdx));
    }

    @GetMapping("/questionlist")
    public ResponseEntity<List<QuestionVO>> questionlist(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        int profileIdx = Integer.parseInt(jwtDecode.getProfile_idx());
        return ResponseEntity.ok(myPageService.questionlist(profileIdx));
    }

    @GetMapping("/reviewlist")
    public ResponseEntity<List<ReviewVO>> reviewlist(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        int profileIdx = Integer.parseInt(jwtDecode.getProfile_idx());
        return ResponseEntity.ok(myPageService.reviewlist(profileIdx));
    }

    @PostMapping("/pwd/chk")
    public ResponseEntity<Integer> pwdchk(@RequestHeader("Authorization") String token, @RequestParam String u_pwd) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.pwdchk(userId, u_pwd));
    }

    @PostMapping("/pwd/update")
    public ResponseEntity<Integer> pwdupdate(@RequestHeader("Authorization") String token, @RequestParam String new_pwd) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.pwdupdate(userId, new_pwd));
    }

    @PostMapping("/pay")
    public ResponseEntity<Integer> pay(@RequestHeader("Authorization") String token, @RequestParam String u_subs) {
        String jwtToken = token.replace("Bearer ", "");
        JwtDecode jwtDecode = new JwtDecode(jwtToken);
        String userId = jwtDecode.getUser_id();
        return ResponseEntity.ok(myPageService.pay(userId, u_subs));
    }
}
