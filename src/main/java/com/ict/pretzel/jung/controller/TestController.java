package com.ict.pretzel.jung.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class TestController {
    
    @GetMapping("test")
    public String test() {
        System.out.println("hello");
        return "bye";
    }
    
    @GetMapping("search")
	public String memberChk() {
		try {
			String apiURL = "\r\n" + //
                                "https://api.themoviedb.org/3/movie/1136496";
			String api_key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYmE1OThkMzg4OTgwZjBlMTJjNmU1N2RkYjRmNjFlNyIsInN1YiI6IjY2NzEzMGNlNDA1YjNhMjk3MDZhYWFlNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cT8hOciOWfO-qUWSh_fzqQzVburxqSAqwdXoaTgHz1E";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			
			// 헤더 요청
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			int responeseCode = conn.getResponseCode();
			System.out.println(responeseCode);
			if(responeseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line ="";
				StringBuffer sb2 = new StringBuffer();
				while((line=br.readLine()) !=null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				System.out.println(result);
				return result;
			}
			
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}
	@GetMapping("detail")
	public String detail(@RequestParam("movie_id") String movie_id) {
		try {
			String apiURL = "\r\n" + //
                                "https://api.themoviedb.org/3/movie/"+movie_id;
			String api_key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYmE1OThkMzg4OTgwZjBlMTJjNmU1N2RkYjRmNjFlNyIsInN1YiI6IjY2NzEzMGNlNDA1YjNhMjk3MDZhYWFlNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cT8hOciOWfO-qUWSh_fzqQzVburxqSAqwdXoaTgHz1E";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			
			// 헤더 요청
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			int responeseCode = conn.getResponseCode();
			System.out.println(responeseCode);
			if(responeseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line ="";
				StringBuffer sb2 = new StringBuffer();
				while((line=br.readLine()) !=null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				System.out.println(result);
				return result;
			}
			
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}
}
