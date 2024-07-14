package com.ict.pretzel.lee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ict.pretzel.lee.mapper.MovieDetailMapper;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.WishVO;
import com.ict.pretzel.vo.ReportVO;

@Service
public class MovieDetailService {

    @Autowired
    private MovieDetailMapper movieDetailMapper; // MovieDetailMapper 빈을 주입받음

    public int addReview(ReviewVO review) {
        // 리뷰를 추가하는 메서드
        return movieDetailMapper.addReview(review); // 매퍼를 호출하여 리뷰를 추가
    }

    public int deleteReview(String profileIdx, String reviewIdx) {
        // 리뷰를 삭제하는 메서드
        return movieDetailMapper.deleteReview(profileIdx, reviewIdx); // 매퍼를 호출하여 리뷰를 삭제
    }

    public int addReport(ReportVO report) {
        // 신고를 추가하는 메서드
        return movieDetailMapper.addReport(report); // 매퍼를 호출하여 신고를 추가
    }

    public int addWish(String profileIdx, String movieIdx) {
        // 찜을 추가하는 메서드
        return movieDetailMapper.addWish(profileIdx, movieIdx); // 매퍼를 호출하여 찜을 추가
    }

    public int deleteWish(String profileIdx, String movieIdx) {
        // 찜을 삭제하는 메서드
        return movieDetailMapper.deleteWish(profileIdx, movieIdx); // 매퍼를 호출하여 찜을 삭제
    }
    public int wishChk(WishVO wishVO) {
        // 찜을 삭제하는 메서드
        return movieDetailMapper.wishChk(wishVO); // 매퍼를 호출하여 찜을 삭제
    }
}
