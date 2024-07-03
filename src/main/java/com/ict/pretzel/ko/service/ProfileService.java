package com.ict.pretzel.ko.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ict.pretzel.jwt.JwtDecode;
import com.ict.pretzel.ko.mapper.ProfileMapper;
import com.ict.pretzel.vo.ProfileVO;

@Service
public class ProfileService {

    @Autowired
    private ProfileMapper profileMapper;
    
    // 프로필 리스트
    public ResponseEntity<?> profile_list(String user_id){
        List<ProfileVO> profile_list = profileMapper.profile_list(user_id);
        return ResponseEntity.ok(profile_list);
    };

    // 프로필 추가
    @Value("${upload.path}")
    private String path;

    public ResponseEntity<?> profile_insert(ProfileVO profile, String token){
        try {
            JwtDecode jwtDecode = new JwtDecode(token);
            profile.setUser_id(jwtDecode.getUser_id());

            // 이미지 저장
            MultipartFile img_file = profile.getImg_file();
            if (img_file == null || img_file.isEmpty()) {
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
        return ResponseEntity.ok(0);
    }

    // 프로필 상세
    public ResponseEntity<?> profile_detail(String profile_idx){
        ProfileVO profile = profileMapper.profile_detail(profile_idx);
        return ResponseEntity.ok(profile);
    }
    
    // 프로필 수정
    public ResponseEntity<?> profile_update(ProfileVO profile){
        try {
            // 원래 프로필 정보 가져오기 
            ProfileVO original_profile = profileMapper.profile_detail(profile.getProfile_idx());
            // 프로필 이름 수정
            original_profile.setName(profile.getName());

            // 이미지 수정
            MultipartFile img_file = profile.getImg_file();
            if (!img_file.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                String img_name = uuid + "_" + img_file.getOriginalFilename();
                original_profile.setImg_name(img_name);
				
                byte[] in = img_file.getBytes();
                File out = new File(path, img_name);
                FileCopyUtils.copy(in, out);
            }
            int result = profileMapper.profile_update(original_profile);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println("profile_update : " + e);
        }
        return ResponseEntity.ok(0);
    }

    
}
