package com.ict.pretzel.lee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.pretzel.lee.mapper.MyPageMapper;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.UserVO;
import com.ict.pretzel.vo.WishVO;

@Service
public class MyPageService {

    @Autowired
    private MyPageMapper myPageMapper;

    public UserVO getUserDetail(String token) {
        return myPageMapper.getUserDetail(token);
    }

    public List<MovieVO> getWatchList(String token) {
        return myPageMapper.getWatchList(token);
    }

    public List<WishVO> getWishList(String token) {
        return myPageMapper.getWishList(token);
    }

    public List<QuestionVO> getQuestionList(String token) {
        return myPageMapper.getQuestionList(token);
    }

    public List<ReviewVO> getReviewList(String token) {
        return myPageMapper.getReviewList(token);
    }

    public int checkPassword(String token, String u_pwd) {
        return myPageMapper.checkPassword(token, u_pwd);
    }

    public int updatePassword(String token, String new_pwd) {
        return myPageMapper.updatePassword(token, new_pwd);
    }

    public int pay(String price, String select_lev, String token) {
        return myPageMapper.pay(price, select_lev, token);
    }
}