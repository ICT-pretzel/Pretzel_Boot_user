package com.ict.pretzel.lee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ict.pretzel.lee.mapper.MyPageMapper;
import com.ict.pretzel.jwt.JWTUtil;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.UserVO;
import com.ict.pretzel.vo.WishVO;

@Service
public class MyPageService {

    @Autowired
    private MyPageMapper myPageMapper;

    // JWTUtil 사용하지 않음
    // @Autowired
    // private JWTUtil jwtUtil;

    // public UserVO getUserDetail(String token) {
    //     String userId = jwtUtil.extractUserId(token);
    //     return myPageMapper.getUserDetail(userId);
    // }

    public UserVO getUserDetail(String userId) {
        return myPageMapper.getUserDetail(userId);
    }

    // public List<MovieVO> getWatchList(String token) {
    //     int profileIdx = Integer.parseInt(jwtUtil.extractProfileIdx(token));
    //     return myPageMapper.getWatchList(profileIdx);
    // }

    public List<MovieVO> getWatchList(int profileIdx) {
        return myPageMapper.getWatchList(profileIdx);
    }

    // public List<WishVO> getWishList(String token) {
    //     int profileIdx = Integer.parseInt(jwtUtil.extractProfileIdx(token));
    //     return myPageMapper.getWishList(profileIdx);
    // }

    public List<WishVO> getWishList(int profileIdx) {
        return myPageMapper.getWishList(profileIdx);
    }

    // public List<QuestionVO> getQuestionList(String token) {
    //     int profileIdx = Integer.parseInt(jwtUtil.extractProfileIdx(token));
    //     return myPageMapper.getQuestionList(profileIdx);
    // }

    public List<QuestionVO> getQuestionList(int profileIdx) {
        return myPageMapper.getQuestionList(profileIdx);
    }

    // public List<ReviewVO> getReviewList(String token) {
    //     int profileIdx = Integer.parseInt(jwtUtil.extractProfileIdx(token));
    //     return myPageMapper.getReviewList(profileIdx);
    // }

    public List<ReviewVO> getReviewList(int profileIdx) {
        return myPageMapper.getReviewList(profileIdx);
    }

    // public int checkPassword(String token, String uPwd) {
    //     String userId = jwtUtil.extractUserId(token);
    //     return myPageMapper.checkPassword(userId, uPwd);
    // }

    public int checkPassword(String userId, String uPwd) {
        return myPageMapper.checkPassword(userId, uPwd);
    }

    // public int updatePassword(String token, String newPwd) {
    //     String userId = jwtUtil.extractUserId(token);
    //     return myPageMapper.updatePassword(userId, newPwd);
    // }

    public int updatePassword(String userId, String newPwd) {
        return myPageMapper.updatePassword(userId, newPwd);
    }

    // public int pay(String token, String uSubs) {
    //     String userId = jwtUtil.extractUserId(token);
    //     return myPageMapper.pay(userId, uSubs);
    // }

    public int pay(String userId, String uSubs) {
        return myPageMapper.pay(userId, uSubs);
    }
}
