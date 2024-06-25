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
        //String phone = oAuth2User.getAttribute("phone");
        String id = "";
        // 네이버 추가 정보
        String birth = oAuth2User.getAttribute("birth");
        String gender = oAuth2User.getAttribute("gender");

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
            //uvo.setPhone(phone != null ? phone : "000-0000-0000");
            uvo.setName(name != null ? name : "unknown");
            //uvo.setProvider(provider);
            uvo.setBirth(birth);
            uvo.setGender(gender);

            /* 
            if (provider.equals("kakao")) {
                uvo.setKakao(email);
            }else if (provider.equals("naver")) {
                uvo.setNaver(email);
            }else if (provider.equals("google")){
                uvo.setGoogle(email);
            }
            */

            userMapper.insertUser(uvo);
        }else {
            // 기존(update)
            userMapper.updateUser(uvo);
        }

        return new User(uvo.getUser_id(), "", new ArrayList<>());
    }
}
