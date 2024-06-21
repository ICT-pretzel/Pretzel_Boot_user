package com.ict.pretzel.ko.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    // 아이디 체크
    public ResponseEntity<?> id_check(UserVO user){
        int result = userMapper.id_check(user.getUser_id());
        return ResponseEntity.ok(result);
    } 

    // 이메일 체크
    public ResponseEntity<?> email_check(UserVO user){
        int result = userMapper.email_check(user.getEmail());
        return ResponseEntity.ok(result);
    } 

    // 회원가입
    public ResponseEntity<?> join(UserVO user){
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        int result = userMapper.join(user);
        return ResponseEntity.ok(result);
    }

    // 아이디 찾기
    public ResponseEntity<?> find_id(UserVO user){
        String user_id = userMapper.find_id(user);
        if (user_id != null) {
            return ResponseEntity.ok(user_id);
        }
        return ResponseEntity.ok("0");
    }

    @Autowired
    private MailService mailService;

    // 비밀번호 찾기
    public ResponseEntity<?> find_pwd(UserVO user){
        String user_id = userMapper.find_pwd(user);
        if (user_id != null) {
            //	인증번호 6자리 만들기
            Random random = new Random();
            String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
             if(randomNumber.length() < 6) {
                int substract = 6 - randomNumber.length();
                StringBuffer sb = new StringBuffer();
                for(int i=0; i<substract; i++) {
                    sb.append("0");
                }
                    
                sb.append(randomNumber);
                randomNumber = sb.toString();
            }
            //	사용자 이메일로 인증번호 보내기
            mailService.sendEmail(randomNumber, user.getEmail());
            
            // 맵에 넣어서 보내기
            Map<String, String> map = new HashMap<String, String>();
            map.put("user_id", user_id);
            map.put("number", randomNumber);
            return ResponseEntity.ok(map);
        }
        return ResponseEntity.ok("0");
    }

    // 새 비밀번호 설정
    public ResponseEntity<?> pwd_update(String user_id, String pwd){
        UserVO user = new UserVO();
        user.setUser_id(user_id);
        user.setPwd(passwordEncoder.encode(pwd));
        // DB 에 업데이트하기
        int result = userMapper.pwd_update(user);
        return ResponseEntity.ok(result);
    }

    // 유저 상세
    public ResponseEntity<?> detail(String user_id){
        UserVO user = userMapper.login(user_id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok("0");
    }

    


}
