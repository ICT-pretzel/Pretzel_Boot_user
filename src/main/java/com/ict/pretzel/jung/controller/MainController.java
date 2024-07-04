package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jung.service.MainService;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.ProfileVO;
import com.ict.pretzel.vo.StatisticsVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

@RestController
@RequestMapping("/main")
public class MainController {
    
	@Autowired
	private MainService mainService;
	
	@PostMapping("/recent_list")
	public ResponseEntity<?> recent_list() {
		try {
			List<MovieVO> result = mainService.recent_list();
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}

	@PostMapping("/thema_list")
	public ResponseEntity<?> thema_list() {
		try {
			List<MovieVO> horror_list = mainService.thema_list("공포");
			List<MovieVO> romance_list = mainService.thema_list("로맨스");
			List<MovieVO> comic_list = mainService.thema_list("코믹");
			List<MovieVO> crime_list = mainService.thema_list("범죄/스릴러");
			List<MovieVO> action_list = mainService.thema_list("액션");
			List<MovieVO> ani_list = mainService.thema_list("애니메이션");
			Map<String,Object> result = new HashMap<>();
			result.put("공포", horror_list);
			result.put("로맨스", romance_list);
			result.put("코믹", comic_list);
			result.put("범죄/스릴러", crime_list);
			result.put("액션", action_list);
			result.put("애니메이션", ani_list);
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@PostMapping("/statistics_list")
	public ResponseEntity<?> statistics_list(@RequestBody ProfileVO profileVO) {
		try {
			Random random = new Random();
			StatisticsVO statisticsVO = new StatisticsVO();
			String age = "";
			String gender="";
			String thema = "";
			int b_age = profileVO.getAge();
			if (b_age > 0 && b_age < 20) {
				age = "10";
			}else if (b_age >= 20 && b_age < 30) {
				age = "20";
			}else if (b_age >= 30 && b_age < 40) {
				age = "30";
			}else if (b_age >= 40 && b_age < 50) {
				age = "40";
			}else if (b_age >= 50) {
				age = "50";
			}

			String b_gender = profileVO.getGender();
			if (b_gender.equals("0")) {
				gender = "woman";
			}else if (b_gender.equals("1")) {
				gender = "man";
			}
			List<String> list = profileVO.getLike_thema();
			thema = list.get(random.nextInt(list.size()));
			
			Map<String,String> rand_map = new HashMap<>();
			rand_map.put("age", age);
			rand_map.put("gender", gender);
			rand_map.put("thema", thema);

			List<String> rand_list = new ArrayList<>();
			rand_list.add("age");
			rand_list.add("gender");
			rand_list.add("thema");
			Collections.shuffle(rand_list);
			List<String> select_list = rand_list.subList(0,2);
			System.out.println(thema);
			for (String k : select_list) {
				System.out.println(k);
			}
			List<String> rand_result = null;
			List<MovieVO> movie_result = null;
			Map<String,Object> result = null;
			if (select_list.contains("age") &&select_list.contains("gender")) {
				System.out.println("age_gender_list");
				statisticsVO.setAge(rand_map.get("age"));
				statisticsVO.setGender(rand_map.get("gender"));
				movie_result = mainService.age_gender_list(statisticsVO);
			}else if (select_list.contains("thema") &&select_list.contains("gender")) {
				System.out.println("thema_gender_list");
				statisticsVO.setThema(rand_map.get("thema"));
				statisticsVO.setGender(rand_map.get("gender"));
				movie_result =mainService.thema_gender_list(statisticsVO);
			}else if (select_list.contains("thema") &&select_list.contains("age")) {
				System.out.println("thema_age_list");
				statisticsVO.setThema(rand_map.get("thema"));
				statisticsVO.setAge(rand_map.get("age"));
				movie_result = mainService.thema_age_list(statisticsVO);
			}
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}
	@PostMapping("/day_list")
	public ResponseEntity<?> day_list() {
		try {
			List<MovieVO> result = null;
			result = mainService.day_list();
			if (result.size() < 10) {
				result = mainService.day_backup_list();
				if (result.size() < 10) {
					result = mainService.spare_list();
				}
			}
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}

}