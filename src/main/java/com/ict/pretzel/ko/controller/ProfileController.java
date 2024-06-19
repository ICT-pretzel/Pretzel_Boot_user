package com.ict.pretzel.ko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.ko.service.ProfileService;
import com.ict.pretzel.vo.ProfileVO;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    // 로그인 후 프로필 리스트 보여주기
    @PostMapping("/list")
    public ResponseEntity<?> profile_list(@RequestBody String user_id) {
        return profileService.profile_list(user_id);
    }

    // 프로필 선택시 토큰 만들어서 주기
    @PostMapping("/login")
    public ResponseEntity<?> profile_login(@RequestBody ProfileVO profile) {
        return profileService.profile_login(profile);
    }
    
    // 프로필 추가
    @PostMapping("/insert")
    public ResponseEntity<?> profile_insert(@RequestBody ProfileVO profile) {
        return profileService.profile_insert(profile);
    }
    
    // 프로필 상세
    @PostMapping("/detail")
    public ResponseEntity<?> profile_detaile(@RequestBody String token) {
        return profileService.profile_detaile(token);
    }
    
    

}
