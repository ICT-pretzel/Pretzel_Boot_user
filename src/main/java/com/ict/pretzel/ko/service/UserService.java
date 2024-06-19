package com.ict.pretzel.ko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.pretzel.ko.mapper.UserMapper;
import com.ict.pretzel.vo.UserVO;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> join(UserVO user){
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        int result = userMapper.join(user);
        return ResponseEntity.ok(result);
    }

}
