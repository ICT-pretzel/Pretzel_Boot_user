package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jung.service.MovieService;
import com.ict.pretzel.vo.CastVO;
import com.ict.pretzel.vo.CrewVO;
import com.ict.pretzel.vo.MovieReviewVO;
import com.ict.pretzel.vo.MovieVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/movie")
public class MovieController {
    
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movie_detail")
	public ResponseEntity<?> movie_detail(@RequestParam String movie_idx) {
		try {
			MovieVO result = movieService.movie_detail(movie_idx);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/cast_list")
	public ResponseEntity<?> cast_list(@RequestParam String movie_idx) {
		try {
			List<CastVO> result = movieService.cast_list(movie_idx);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/crew_list")
	public ResponseEntity<?> crew_list(@RequestParam String movie_idx) {
		try {
			List<CrewVO> result = movieService.crew_list(movie_idx);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/movie_review_list")
	public ResponseEntity<?> movie_review_list(@RequestParam String movie_idx) {
		try {
			List<MovieReviewVO> result = movieService.movie_review_list(movie_idx);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}

}