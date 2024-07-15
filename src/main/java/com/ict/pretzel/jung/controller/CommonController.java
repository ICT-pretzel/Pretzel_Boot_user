package com.ict.pretzel.jung.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/common")
public class CommonController {
    @GetMapping("/image")
    public ResponseEntity<?> image(@RequestParam("imageName") String imageName) {     
    String path = "src\\main\\resources\\upload\\"; //이미지가 저장된 위치 
    Resource resource = new FileSystemResource(path + imageName);
        return new ResponseEntity<>(resource, HttpStatus.OK); 
    }
}
