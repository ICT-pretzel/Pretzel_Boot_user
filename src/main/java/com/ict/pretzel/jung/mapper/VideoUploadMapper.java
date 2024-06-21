package com.ict.pretzel.jung.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.pretzel.vo.VideoUploadVO;

@Mapper
public interface VideoUploadMapper {
    int video_insert(@Param("videoUploadVO") VideoUploadVO videoUploadVO);
    int thema_insert(@Param("videoUploadVO") VideoUploadVO videoUploadVO);
}