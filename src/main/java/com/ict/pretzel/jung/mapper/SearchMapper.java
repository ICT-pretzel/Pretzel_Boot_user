package com.ict.pretzel.jung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.CastMovieVO;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.RealtimeOptionVO;

@Mapper
public interface SearchMapper {
    List<MovieVO> select_thema(String thema);
    List<MovieVO> select_movie(String keyword);
    List<CastMovieVO> select_cast(String keyword);
    List<MovieVO> realtime_list();
    List<MovieVO> add_list(RealtimeOptionVO realtimeOptionVO);
}