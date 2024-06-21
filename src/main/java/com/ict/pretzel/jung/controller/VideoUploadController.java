package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ict.pretzel.vo.VideoUploadVO;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/upload")
public class VideoUploadController {
    
    @Value("${spring.cloud.gcp.storage.bucket}") // application.properties에 써둔 bucket 이름
    private String bucketName;
    private final Storage storage;

	@PostMapping("/video")
    public ResponseEntity<Void> updateMemberInfo(VideoUploadVO videoUploadVO) throws IOException {

        String uuid = videoUploadVO.getVideo().getOriginalFilename()+"_"+UUID.randomUUID().toString();
        String ext = videoUploadVO.getVideo().getContentType(); // 파일의 형식 ex) JPG

        System.out.println(uuid);
        System.out.println(ext);
		// Cloud에 영상 업로드
        BlobInfo blobInfo = storage.createFrom(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(ext)
                        .build(),
                        videoUploadVO.getVideo().getInputStream());
        System.out.println("테스트");
        return new ResponseEntity(HttpStatus.OK);
    }
}
