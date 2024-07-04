package com.ict.pretzel.jung.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.pretzel.jung.mapper.SearchMapper;
import com.ict.pretzel.vo.CastMovieVO;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.RealtimeOptionVO;

@Service
public class SearchService {
    
    @Autowired
    private SearchMapper searchMapper;

	public List<MovieVO> select_thema(String thema) {
        List<MovieVO> result = searchMapper.select_thema(thema);
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> select_movie(String keyword) {
        List<MovieVO> result = searchMapper.select_movie(keyword);
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<CastMovieVO> select_cast(String keyword) {
        List<CastMovieVO> result = searchMapper.select_cast(keyword);
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> realtime_list() {
        List<MovieVO> result = searchMapper.realtime_list();
        if (result != null) {
            return result;
        }
        return null;
	}
	public List<MovieVO> add_list(RealtimeOptionVO realtimeOptionVO) {
        List<MovieVO> result = searchMapper.add_list(realtimeOptionVO);
        if (result != null) {
            return result;
        }
        return null;
	}
}
