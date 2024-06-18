package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
public class TestController {
    
    @GetMapping("test")
    public String test() {
        System.out.println("hello");
        return "bye";
    }
    
    @GetMapping("search")
    public String search(){
        System.out.println("search");
        return "search end";
    }
}
