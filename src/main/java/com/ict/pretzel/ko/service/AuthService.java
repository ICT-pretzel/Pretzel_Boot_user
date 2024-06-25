package com.ict.pretzel.ko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ict.pretzel.jwt.JWTUtil;
import com.ict.pretzel.jwt.JwtResponse;
import com.ict.pretzel.ko.mapper.ProfileMapper;
import com.ict.pretzel.ko.mapper.UserMapper;
import com.ict.pretzel.vo.UserVO;

@Service
public class AuthService {
 
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ProfileMapper profileMapper;

    public ResponseEntity<?> login(UserVO user){
        try {
            // 로그인 정보가 DB 에 있는지 여부 체크
            Authentication authentication 
            = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUser_id(), user.getPwd()));

            // 마지막 로그인 업데이트
            userMapper.updateUser(user);

            // 사용자의 아이디와 패스워드를 가지고 있다.
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUser_id());

            // 그거를 가지고 jwt 토큰을 만든다.
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            // 틀리면 0 리턴
            System.out.println("login : " + e);
            return ResponseEntity.status(401).body("0");
        }
    }


}
