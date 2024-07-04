package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jung.service.SearchService;
import com.ict.pretzel.vo.CastMovieVO;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.RealtimeOptionVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/search")
public class SearchController {
    
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/select_thema")
	public ResponseEntity<?> select_thema(@RequestParam String thema) {
		try {
			List<MovieVO> result = searchService.select_thema(thema);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/keyword_list")
	public ResponseEntity<?> keyword_list(@RequestParam String keyword) {
		try {
			List<MovieVO> movie_result = searchService.select_movie(keyword);
			List<CastMovieVO> cast_result = searchService.select_cast(keyword);
			Map<String, Object> result = new HashMap<>();
			result.put("movie", movie_result);
			result.put("cast", cast_result);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/realtime_list")
	public ResponseEntity<?> realtime_list() {
		try {
			List<MovieVO> result = searchService.realtime_list();
			if (result.size() < 10) {
				int add_num = 10 - result.size();
				List<String> mir_idx = new ArrayList<>();
				for (MovieVO k : result) {
					System.out.println(k.getMovie_idx());
					mir_idx.add(k.getMovie_idx());
				}
				RealtimeOptionVO realtimeOptionVO = new RealtimeOptionVO();
				realtimeOptionVO.setAdd_num(add_num);
				realtimeOptionVO.setMir_idx(mir_idx);
				List<MovieVO> add_list = searchService.add_list(realtimeOptionVO);
				result.addAll(add_list);
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
}