package com.ict.pretzel.ko.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ict.pretzel.jwt.JWTUtil;
import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.ko.mapper.ProfileMapper;
import com.ict.pretzel.ko.mapper.UserMapper;
import com.ict.pretzel.vo.ProfileVO;
import com.ict.pretzel.vo.UserVO;

@Service
public class ProfileService {

    @Autowired
    private ProfileMapper profileMapper;
    
    // 프로필 리스트
    public ResponseEntity<?> profile_list(String user_id){
        List<ProfileVO> profile_list = profileMapper.profile_list(user_id);
        // 프로필이 없으면 user_id 만 리턴
        if (profile_list.isEmpty()) {
            return ResponseEntity.ok(user_id);
        }
        // 프로필이 있으면 리스트 리턴
        //(프로필VO 안에 user_id 있어서 따로 리턴안함)
        return ResponseEntity.ok(profile_list);
    };

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    // 프로필 추가
    @Value("${upload.path}")
    private String path;

    public ResponseEntity<?> profile_insert(MultipartFile img_file, ProfileVO profile, String token){
        try {
            JwtDecode jwtDecode = new JwtDecode(token);
            profile.setUser_id(jwtDecode.getUser_id());

            if (img_file.isEmpty()) {
                profile.setImg_name("");
            }else {
                UUID uuid = UUID.randomUUID();
                String img_name = uuid + "_" + img_file.getOriginalFilename();
                profile.setImg_name(img_name);
				
                byte[] in = img_file.getBytes();
                File out = new File(path, img_name);
                FileCopyUtils.copy(in, out);
            }

            int result = profileMapper.profile_insert(profile);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println("profile_insert : " + e);
        }
        return ResponseEntity.ok("0");
    }

    // 프로필 상세
    public ResponseEntity<?> profile_detail(String profile_idx){
        ProfileVO profile = profileMapper.profile_detail(profile_idx);
        System.out.println(profile.getLike_thema());
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.ok("0");
    }
    
    // 프로필 수정
    public ResponseEntity<?> profile_update(MultipartFile img_file, ProfileVO profile){
        try {
            // 원래 프로필 정보 가져오기 
            ProfileVO profile2 = profileMapper.profile_detail(profile.getProfile_idx());
            // 프로필 이름 수정
            profile2.setName(profile.getName());
            // 이미지 수정
            if (!img_file.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                String img_name = uuid + "_" + img_file.getOriginalFilename();
                profile2.setImg_name(img_name);
				
                byte[] in = img_file.getBytes();
                File out = new File(path, img_name);
                FileCopyUtils.copy(in, out);
            }
            int result = profileMapper.profile_update(profile2);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println("profile_update : " + e);
        }
        return ResponseEntity.ok("0");
    }

    
}
