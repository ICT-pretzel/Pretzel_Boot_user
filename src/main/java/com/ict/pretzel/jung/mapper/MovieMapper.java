package com.ict.pretzel.jung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.CastVO;
import com.ict.pretzel.vo.CrewVO;
import com.ict.pretzel.vo.MovieReviewVO;
import com.ict.pretzel.vo.MovieVO;

@Mapper
public interface MovieMapper {
    MovieVO movie_detail(String movie_idx);
    int real_stackup(String movie_idx);
    List<CastVO> cast_list(String movie_idx);
    List<CrewVO> crew_list(String movie_idx);
    List<MovieReviewVO> movie_review_list(String movie_idx);
}