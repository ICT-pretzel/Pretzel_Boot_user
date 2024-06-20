package com.ict.pretzel.jung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.pretzel.vo.MovieVO;

@Mapper
public interface SearchMapper {
    List<MovieVO> thema_list(@Param("thema") String thema);
}