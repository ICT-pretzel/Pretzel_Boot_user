package com.ict.pretzel.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MovieVO {
    private String movie_idx, title, thema, director, actor, synopsis, video_path, poster_path;
    private MultipartFile video_file, poster_file;
}
