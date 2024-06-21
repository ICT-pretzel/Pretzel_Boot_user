package com.ict.pretzel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ict.pretzel.jung.mapper.VideoUploadMapper;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.VideoUploadVO;

@Service
public class VideoUploadService {

        @Autowired
        private VideoUploadMapper videoUploadMapper;
        
    	public int video_insert(VideoUploadVO videoUploadVO) {
        int thema_list = videoUploadMapper.video_insert(videoUploadVO);

        if (thema_list < 0) {
            return 0;
        }
        return 1;
	}
    	public int thema_insert(VideoUploadVO videoUploadVO) {
        int thema_list = videoUploadMapper.thema_insert(videoUploadVO);

        if (thema_list < 0) {
            return 0;
        }
        return 1;
	}
}
