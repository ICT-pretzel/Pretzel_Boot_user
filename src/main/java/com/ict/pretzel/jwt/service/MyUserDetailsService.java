package com.ict.pretzel.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ict.pretzel.ko.mapper.UserMapper;
import com.ict.pretzel.vo.UserVO;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserVO user = userMapper.login(user_id);
        // sns 로그인 시 패스워드가 없음
        if (user.getPwd() == null) {
            return new User(user.getUser_id(), "", new ArrayList<>());
        }
        // 일반 로그인
        return new User(user.getUser_id(), user.getPassword(), new ArrayList<>());
    }

    // 개인정보 추출 
    public UserVO getUserDetail(String user_id) throws Exception{
        UserVO uvo = userMapper.login(user_id);
        return uvo;
    }

    public UserDetails loadUserByOAuth2User(OAuth2User oAuth2User, String provider){
        // 공통 정보
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String id = "";

        // 카카오는 id 가 long 형으로 들어온다.(끄집어와서 바꿔야만 가능)
        if (provider.equals("kakao")) {
            Long kakaoId = oAuth2User.getAttribute("id");
            id = String.valueOf(kakaoId);
        }else {
            id = oAuth2User.getAttribute("id");
        }

        // 이메일로 유저가 있는지 없는지 검사한다.
        // DB 에 있으면 update, 없으면 insert
        UserVO uvo = userMapper.findUserByEmail(email);

        if (uvo == null) {
            // 신규(insert)
            uvo = new UserVO();
            uvo.setUser_id(id);
            uvo.setEmail(email);
            uvo.setName(name != null ? name : "unknown");

            userMapper.insertUser(uvo);
        }else {
            // 기존(update)
            userMapper.updateUser(uvo);
        }

        return new User(uvo.getUser_id(), "", new ArrayList<>());
    }
}
