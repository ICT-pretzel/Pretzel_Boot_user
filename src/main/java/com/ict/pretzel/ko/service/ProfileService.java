package com.ict.pretzel.ko.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ict.pretzel.jwt.JWTUtil;
import com.ict.pretzel.ko.mapper.ProfileMapper;
import com.ict.pretzel.vo.ProfileVO;

@Service
public class ProfileService {

    @Autowired
    private ProfileMapper profileMapper;
    
    // 프로필 리스트
    public ResponseEntity<?> profile_list(String user_id){
        List<ProfileVO> profiles = profileMapper.profile_list(user_id);
        // 프로필이 없으면 user_id 만 리턴
        if (profiles == null) {
            return ResponseEntity.ok(user_id);
        }
        // 프로필이 있으면 리스트 리턴
        //(프로필VO 안에 user_id 있어서 따로 리턴안함)
        return ResponseEntity.ok(profiles);
    };

    @Autowired
    private JWTUtil jwtUtil;

    // 프로필 선택
    public ResponseEntity<?> profile_login(ProfileVO profile){
        // 토큰 생성
        final String token = jwtUtil.generateToken(profile.getUser_id(), profile.getProfile_idx());
        // 프로필 정보 가져오기
        profile = profileMapper.profile_detail(profile.getProfile_idx());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("profile", profile);
        return ResponseEntity.ok(map);
    }

    // 프로필 추가
    @Value("${upload.path}")
    private String path;
    public ResponseEntity<?> profile_insert(ProfileVO profile){
        try {
            MultipartFile img_file = profile.getImg_file();
            if (img_file.isEmpty()) {
                profile.setImg_name("");
            }else {
                UUID uuid = UUID.randomUUID();
                String img_name = uuid + "_" + img_file.getOriginalFilename();
                profile.setImg_name(img_name);;
				
                byte[] in = img_file.getBytes();
                File out = new File(path, img_name);
                FileCopyUtils.copy(in, out);
            }
            int result = profileMapper.profile_insert(profile);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println("profile_insert : " + e);
        }
        return ResponseEntity.ok(null);
    }

    // 프로필 상세
    public ResponseEntity<?> profile_detaile(String token){


        
        return null;
    }


    
}
