package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.pretzel.jung.service.SearchService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/search")
public class SearchController {
    
	@Autowired
	private SearchService searchService;

	@GetMapping("/thema_list")
	public ResponseEntity<?> thema_list(@RequestParam("thema") String thema) {
		return searchService.thema_list(thema);
	}
}