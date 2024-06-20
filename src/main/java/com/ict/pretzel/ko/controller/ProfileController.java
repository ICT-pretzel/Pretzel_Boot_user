package com.ict.pretzel.ko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.ko.service.ProfileService;
import com.ict.pretzel.ko.vo.TokenVO;
import com.ict.pretzel.vo.ProfileVO;
import com.ict.pretzel.vo.UserVO;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    // 로그인 후 프로필 리스트 보여주기
    @PostMapping("/list")
    public ResponseEntity<?> profile_list(@RequestBody TokenVO token) {
        System.out.println(token.getUser_id());
        return profileService.profile_list(token.getUser_id());
    }

    // 프로필 선택시 토큰 만들어서 주기
    @PostMapping("/login")
    public ResponseEntity<?> profile_login(@RequestBody TokenVO token) {
        return profileService.profile_login(token);
    }
    
    // 프로필 추가
    @PostMapping("/insert")
    public ResponseEntity<?> profile_insert(@RequestParam("img_file") MultipartFile img_file,
        @RequestParam("name") String name, @RequestParam("user_id") String user_id) {
            ProfileVO profile = new ProfileVO();
            profile.setImg_file(img_file);
            profile.setName(name);
            profile.setUser_id(user_id);
        return profileService.profile_insert(profile);
    }
    
    // 프로필 상세
    @PostMapping("/detail")
    public ResponseEntity<?> profile_detaile(@AuthenticationPrincipal JwtDecode jwtDecode) {
        return profileService.profile_detaile(jwtDecode.getProfile_idx());
    }
    
    // 프로필 수정
    @PostMapping("/update")
    public ResponseEntity<?> profile_update(@RequestParam("img_file") MultipartFile img_file,
    @RequestParam("name") String name, @RequestParam("profile_idx") String profile_idx) {
        return profileService.profile_update(img_file, name, profile_idx);
    }
    
    

}
