package com.ict.pretzel.jung.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.pretzel.jung.mapper.MainMapper;
import com.ict.pretzel.vo.MovieVO;

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
}
