package com.ict.pretzel.jung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.MovieVO;

@Mapper
public interface SearchMapper {
    List<MovieVO> select_thema(String thema);
}