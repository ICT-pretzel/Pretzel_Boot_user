package com.ict.pretzel.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class VideoUploadVO {
    private String original_title, trans_title, movie_id, thema;
    private MultipartFile video;
}
