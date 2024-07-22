package com.ict.pretzel.ko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.ko.service.ProfileService;
import com.ict.pretzel.vo.ProfileVO;


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

    // 프로필 추가(이미지 있을때)
    @PostMapping("/profile_insert")
    public ResponseEntity<?> profile_insert(@RequestHeader("Authorization") String token, 
                                            ProfileVO profile) {
        return profileService.profile_insert(profile, token);
    }

    // 프로필 추가(이미지 없을때)
    @PostMapping("/profile_insert2")
    public ResponseEntity<?> profile_insert2(@RequestHeader("Authorization") String token, 
                                            @RequestBody ProfileVO profile) {
        return profileService.profile_insert(profile, token);
    }
    
    // 프로필 상세
    @PostMapping("/profile_detail")
    public ResponseEntity<?> profile_detail(@RequestBody ProfileVO profile) {
        return profileService.profile_detail(profile.getProfile_idx());
    }
    
    // 프로필 수정(이미지 있을때)
    @PostMapping("/profile_update")
    public ResponseEntity<?> profile_update(ProfileVO profile) {
        System.out.println("!!" + profile);
        return profileService.profile_update(profile);
    }

    // 프로필 수정(이미지 없을때)
    @PostMapping("/profile_update2")
    public ResponseEntity<?> profile_update2(@RequestBody ProfileVO profile) {
        return profileService.profile_update(profile);
    }
    
    // 프로필 삭제
    @PostMapping("/profile_delete")
    public ResponseEntity<?> profile_delete(@RequestBody ProfileVO profile) {
        return profileService.profile_delete(profile.getProfile_idx());
    }
    

}
