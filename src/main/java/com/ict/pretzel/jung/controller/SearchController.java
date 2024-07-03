package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jung.service.SearchService;
import com.ict.pretzel.vo.MovieVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
	// @GetMapping("/keyword_list")
	// public ResponseEntity<?> keyword_list(@RequestParam String keyword) {
	// 	try {
	// 		List<MovieVO> result = searchService.select_movie(keyword);
	// 		List<MovieVO> result = searchService.select_cast(keyword);
	// 		return ResponseEntity.ok(result);
			
	// 	} catch (Exception e) {
	// 		System.out.println(e);
	// 		return ResponseEntity.ok("0");
	// 	}
	// }
}