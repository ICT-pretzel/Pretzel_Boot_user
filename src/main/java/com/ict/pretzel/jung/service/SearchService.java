package com.ict.pretzel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ict.pretzel.jung.mapper.SearchMapper;
import com.ict.pretzel.vo.MovieVO;

@Service
public class SearchService {
    
    @Autowired
    private SearchMapper searchMapper;

	public ResponseEntity<?> thema_list(String thema) {
        List<MovieVO> thema_list = searchMapper.thema_list(thema);

        if (thema_list == null) {
            return ResponseEntity.ok(0);
        }
        return ResponseEntity.ok(thema_list);
	}
}
