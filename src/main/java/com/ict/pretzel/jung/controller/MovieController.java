package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jung.service.MovieService;
import com.ict.pretzel.vo.CastVO;
import com.ict.pretzel.vo.CrewVO;
import com.ict.pretzel.vo.MovieReviewVO;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.ProfileVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/movie")
public class MovieController {
    
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movie_detail")
	public ResponseEntity<?> movie_detail(@RequestParam("movie_idx") String movie_idx) {
		try {
			MovieVO result = movieService.movie_detail(movie_idx);
			int real_stack = movieService.real_stackup(movie_idx);
	
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/cast_list")
	public ResponseEntity<?> cast_list(@RequestParam("movie_idx") String movie_idx) {
		try {
			List<CastVO> result = movieService.cast_list(movie_idx);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/crew_list")
	public ResponseEntity<?> crew_list(@RequestParam("movie_idx") String movie_idx) {
		try {
			List<CrewVO> result = movieService.crew_list(movie_idx);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/movie_review_list")
	public ResponseEntity<?> movie_review_list(@RequestParam("movie_idx") String movie_idx) {
		try {
			List<MovieReviewVO> result = movieService.movie_review_list(movie_idx);
			List<MovieReviewVO> result_sub = result;
			double average = result_sub.stream().mapToDouble(x->{
				try {
				return Double.parseDouble(x.getRating());
			} catch (NumberFormatException e) {
				return 0.0;
			}
			}).average().getAsDouble();
			Map<String, Object> data = new HashMap<>();
			data.put("result", result);
			data.put("average", average);
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@GetMapping("/watch_movie")
	public ResponseEntity<?> watch_movie(@RequestParam("gender") String gender, 
	@RequestParam("age") int age, @RequestParam("movie_idx") String movie_idx, 
	@RequestParam("profile_idx") String profile_idx) {
		try {
			String s_age = "";
			if (age > 0 && age < 20) {
				s_age = "10";
			}else if (age >= 20 && age < 30) {
				s_age = "20";
			}else if (age >= 30 && age < 40) {
				s_age = "30";
			}else if (age >= 40 && age < 50) {
				s_age = "40";
			}else if (age >= 50) {
				s_age = "50";
			}
			Map<String, Object> up_stack = new HashMap<>();
			up_stack.put("movie_idx", movie_idx);
			up_stack.put("age", s_age);
			up_stack.put("gender", gender);

			Map<String, Object> watch = new HashMap<>();
			watch.put("profile_idx", profile_idx);
			watch.put("movie_idx", movie_idx);
			int result = movieService.update_stack(up_stack);
			int real_stack = movieService.day_stackup(movie_idx);
			int watchlist = movieService.watchUp(watch);
	
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
}