package com.ict.pretzel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.pretzel.jung.mapper.SearchMapper;
import com.ict.pretzel.vo.MovieVO;

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
}
