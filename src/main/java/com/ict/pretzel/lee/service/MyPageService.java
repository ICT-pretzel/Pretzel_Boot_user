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

    public UserVO getUserDetail(String userId) {
        return myPageMapper.getUserDetail(userId);
    }

    public List<MovieVO> getWatchList(int profileIdx) {
        return myPageMapper.getWatchList(profileIdx);
    }

    public List<WishVO> getWishList(int profileIdx) {
        return myPageMapper.getWishList(profileIdx);
    }

    public List<QuestionVO> getQuestionList(int profileIdx) {
        return myPageMapper.getQuestionList(profileIdx);
    }

    public List<ReviewVO> getReviewList(int profileIdx) {
        return myPageMapper.getReviewList(profileIdx);
    }

    public int checkPassword(String userId, String uPwd) {
        return myPageMapper.checkPassword(userId, uPwd);
    }

    public int updatePassword(String userId, String newPwd) {
        return myPageMapper.updatePassword(userId, newPwd);
    }

    public int pay(String userId, String uSubs) {
        return myPageMapper.pay(userId, uSubs);
    }
}
