package com.ict.pretzel.ko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ict.pretzel.vo.UserVO;

@Service
public class AuthService {
 
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public ResponseEntity<?> login(UserVO user){
        try {
            // 로그인 정보가 DB 에 있는지 여부 체크
            Authentication authentication 
            = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUser_id(), user.getPwd()));

            // 아이디 비밀번호가 맞으면 user_id 리턴
            return ResponseEntity.ok(user.getUser_id());
        } catch (Exception e) {
            // 틀르면 0 리턴
            System.out.println("login : " + e);
            return ResponseEntity.status(401).body("0");
        }
    }


}
