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
            System.out.println("확인용 : "+profile.getImg_file());
            // 이미지 저장
            if (profile.getImg_file() != null) {
                MultipartFile img_file = profile.getImg_file();
                if (img_file == null || img_file.isEmpty()) {
                    profile.setImg_name("default_profile.png");
                }else {
                    UUID uuid = UUID.randomUUID();
                    String img_name = uuid + "_" + img_file.getOriginalFilename();
                    profile.setImg_name(img_name);
                    
                    byte[] in = img_file.getBytes();
                    File out = new File(path, img_name);
                    FileCopyUtils.copy(in, out);
                }
            }else{
                profile.setImg_name("default_profile.png");
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

            // 이미지 수정
            if (profile.getImg_file() != null) {
                MultipartFile img_file = profile.getImg_file();
                if (!img_file.isEmpty()) {
                    UUID uuid = UUID.randomUUID();
                    String img_name = uuid + "_" + img_file.getOriginalFilename();
                    profile.setImg_name(img_name);
                    
                    byte[] in = img_file.getBytes();
                    File out = new File(path, img_name);
                    FileCopyUtils.copy(in, out);
                }
            }else {
                profile.setImg_name(original_profile.getImg_name());
            }
            int result = profileMapper.profile_update(profile);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println("profile_update : " + e);
        }
        return ResponseEntity.ok(0);
    }

    // 프로필 삭제
    public ResponseEntity<?> profile_delete(String profile_idx) {

        int result = profileMapper.profile_delete(profile_idx);

        return ResponseEntity.ok(result);
    }


}
