package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jung.service.MainService;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.ProfileVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/recent_list")
	public ResponseEntity<?> recent_list() {
		try {
			List<MovieVO> result = mainService.recent_list();
			return ResponseEntity.ok(result);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}

	@GetMapping("/thema_list")
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
			String age = String.valueOf(profileVO.getAge());
			String gender = profileVO.getGender();
			List<String> list = profileVO.getLike_thema();
			String thema = list.get(random.nextInt(list.size()));

			List<String> rand_list = new ArrayList<>();
			rand_list.add(age);
			rand_list.add(gender);
			rand_list.add(thema);
			Collections.shuffle(rand_list);
			List<String> select_list = rand_list.subList(0,2);
			for (String k : select_list) {
				System.out.println(k);
			}
			return ResponseEntity.ok("1");
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("0");
		}
	}

}