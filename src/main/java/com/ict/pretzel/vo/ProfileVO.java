package com.ict.pretzel.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProfileVO {
    private String profile_idx, name, img_name, regdate, user_id, subs;
    private MultipartFile img_file;
}
