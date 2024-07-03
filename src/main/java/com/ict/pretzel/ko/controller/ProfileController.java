package com.ict.pretzel.ko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.ko.service.ProfileService;
import com.ict.pretzel.vo.ProfileVO;
import com.ict.pretzel.vo.UserVO;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    // 로그인 후 프로필 리스트 보여주기
    @PostMapping("/profile_list")
    public ResponseEntity<?> profile_list(@RequestHeader("Authorization") String token) {
        JwtDecode jwtDecode = new JwtDecode(token);
        return profileService.profile_list(jwtDecode.getUser_id());
    }

    // 프로필 추가
    @PostMapping("/profile_insert")
    public ResponseEntity<?> profile_insert(@RequestHeader("Authorization") String token, 
        @RequestParam("img_file") MultipartFile img_file, @ModelAttribute ProfileVO profile) {
        return profileService.profile_insert(img_file, profile, token);
    }
    
    // 프로필 상세
    @PostMapping("/profile_detail")
    public ResponseEntity<?> profile_detail(@RequestBody ProfileVO profile) {
        return profileService.profile_detail(profile.getProfile_idx());
    }
    
    // 프로필 수정
    @PostMapping("/profile_update")
    public ResponseEntity<?> profile_update(@RequestParam("img_file") MultipartFile img_file, 
                                            @ModelAttribute ProfileVO profile) {
        return profileService.profile_update(img_file, profile);
    }
}
