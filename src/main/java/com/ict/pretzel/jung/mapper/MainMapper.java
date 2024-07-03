package com.ict.pretzel.jung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.StatisticsVO;

@Mapper
public interface MainMapper {
    List<MovieVO> recent_list();
    List<MovieVO> thema_list(String thema);
    List<MovieVO> age_gender_list(StatisticsVO statisticsVO);
    List<MovieVO> thema_gender_list(StatisticsVO statisticsVO);
    List<MovieVO> thema_age_list(StatisticsVO statisticsVO);
    List<MovieVO> day_list();
    List<MovieVO> day_backup_list();
    List<MovieVO> spare_list();
}