package com.ict.pretzel.jung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.MovieVO;

@Mapper
public interface MainMapper {
    List<MovieVO> recent_list();
    List<MovieVO> thema_list(String thema);
}