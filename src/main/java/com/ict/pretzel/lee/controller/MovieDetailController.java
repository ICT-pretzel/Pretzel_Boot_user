package com.ict.pretzel.lee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ict.pretzel.lee.service.MovieDetailService;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.ReportVO;
import com.ict.pretzel.vo.ProfileVO;

@RestController
@RequestMapping("/moviedetail")
public class MovieDetailController {

    @Autowired
    private MovieDetailService movieDetailService; // MovieDetailService 빈을 주입받음

    @PostMapping("/review/add")
    public ResponseEntity<?> reviewAdd(@RequestBody ReviewVO review) {
        // 리뷰 추가 요청을 처리하는 메서드
        int result = movieDetailService.addReview(review); // 리뷰 추가 서비스 호출
        return ResponseEntity.ok(result); // 결과를 반환
    }

    @PostMapping("/review/delete")
    public ResponseEntity<?> reviewDelete(@RequestBody ProfileVO profile, @RequestParam String review_idx) {
        // 리뷰 삭제 요청을 처리하는 메서드
        int result = movieDetailService.deleteReview(profile.getProfile_idx(), review_idx); // 리뷰 삭제 서비스 호출
        return ResponseEntity.ok(result); // 결과를 반환
    }

    @PostMapping("/report/add")
    public ResponseEntity<?> reportAdd(@RequestBody ReportVO report) {
        // 신고 추가 요청을 처리하는 메서드
        int result = movieDetailService.addReport(report); // 신고 추가 서비스 호출
        return ResponseEntity.ok(result); // 결과를 반환
    }

    @PostMapping("/wish/add")
    public ResponseEntity<?> wishAdd(@RequestBody ProfileVO profile, @RequestParam String movie_idx) {
        // 찜 추가 요청을 처리하는 메서드
        int result = movieDetailService.addWish(profile.getProfile_idx(), movie_idx); // 찜 추가 서비스 호출
        return ResponseEntity.ok(result); // 결과를 반환
    }

    @PostMapping("/wish/delete")
    public ResponseEntity<?> wishDelete(@RequestBody ProfileVO profile, @RequestParam String movie_idx) {
        // 찜 삭제 요청을 처리하는 메서드
        int result = movieDetailService.deleteWish(profile.getProfile_idx(), movie_idx); // 찜 삭제 서비스 호출
        return ResponseEntity.ok(result); // 결과를 반환
    }
}
