package com.ict.pretzel.ko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jwt.JWTUtil;
import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.jwt.service.MyUserDetailsService;
import com.ict.pretzel.ko.service.AuthService;
import com.ict.pretzel.ko.service.UserService;
import com.ict.pretzel.vo.UserVO;


@RestController
@RequestMapping("/user")
public class UserController {
    
    // 인증
    @Autowired
    private AuthService authService;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserVO user) {
        return authService.login(user);
    }
    
    // 유저 상세
    @PostMapping("/user_detail")
    public ResponseEntity<?> user_detail(@RequestHeader("Authorization") String token) throws Exception {
        // 토큰 가지고 id 를 추출
        JwtDecode jwtDecode = new JwtDecode(token);
        // 추출한 id 로 사용자 정보 추출
        UserVO uvo = myUserDetailsService.getUserDetail(jwtDecode.getUser_id());
        if (uvo != null) {
            return ResponseEntity.ok(uvo);
        }else {
            // 정보가 없으면 0 보내기
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("0");
        }
    }

    // 유저 서비스
    @Autowired
    private UserService userService;

    // 아이디 체크
    @PostMapping("/id_check")
    public ResponseEntity<?> id_check(@RequestBody UserVO user){
        return userService.id_check(user);
    }

    // 이메일 체크
    @PostMapping("/email_check")
    public ResponseEntity<?> email_check(@RequestBody UserVO user){
        return userService.email_check(user);
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserVO user) {
        return userService.join(user);
    }
    
    // 아이디 찾기
    @PostMapping("/find_id")
    public ResponseEntity<?> find_id(@RequestBody UserVO user) {
        return userService.find_id(user);
    }
    
    // 비밀번호 찾기
    @PostMapping("/find_pwd")
    public ResponseEntity<?> find_pwd(@RequestBody UserVO user) {
        return userService.find_pwd(user);
    }

    // 새 비밀번호 설정
    @PostMapping("/new_pwd")
    public ResponseEntity<?> new_pwd(@RequestBody UserVO user) {
        return userService.pwd_update(user.getUser_id(), user.getPwd());
    }

    // 비밀번호 변경
    @PostMapping("/pwd_update")
    public ResponseEntity<?> pwd_update(@RequestHeader("Authorization") String token, 
                                        @RequestBody UserVO user) {
        JwtDecode jwtDecode = new JwtDecode(token);
        return userService.pwd_update(jwtDecode.getUser_id(), user.getPwd());
    }
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 비밀번호 체크
    @PostMapping("/pwd_check")
    public ResponseEntity<?> pwd_check(@RequestHeader("Authorization") String token, 
                                        @RequestBody UserVO user) {
        return userService.pwd_check(token, user.getPwd());
    }
    
    
}