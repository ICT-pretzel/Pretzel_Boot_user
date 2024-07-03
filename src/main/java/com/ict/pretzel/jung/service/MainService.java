package com.ict.pretzel.jung.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.pretzel.jung.mapper.MainMapper;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.StatisticsVO;

@Service
public class MainService {
    
    @Autowired
    private MainMapper mainMapper;

	public List<MovieVO> recent_list() {
        List<MovieVO> result = mainMapper.recent_list();
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> thema_list(String thema) {
        List<MovieVO> result = mainMapper.thema_list(thema);
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> age_gender_list(StatisticsVO statisticsVO) {
        List<MovieVO> result = mainMapper.age_gender_list(statisticsVO);
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> thema_gender_list(StatisticsVO statisticsVO) {
        List<MovieVO> result = mainMapper.thema_gender_list(statisticsVO);
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> thema_age_list(StatisticsVO statisticsVO) {
        List<MovieVO> result = mainMapper.thema_age_list(statisticsVO);
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> day_list() {
        List<MovieVO> result = mainMapper.day_list();
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> day_backup_list() {
        List<MovieVO> result = mainMapper.day_backup_list();
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> spare_list() {
        List<MovieVO> result = mainMapper.spare_list();
        if (result != null) {
            return result;
        }
        return null;
	}
}
