package com.ict.pretzel.lee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ict.pretzel.lee.mapper.MyPageMapper;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.UserVO;
import com.ict.pretzel.vo.WatchListVO;

@Service
public class MyPageService {

    @Autowired
    private MyPageMapper myPageMapper;

    public UserVO userdetail(String userId) {
        return myPageMapper.userdetail(userId);
    }

    public List<WatchListVO> watchlist(int profileIdx) {
        return myPageMapper.watchlist(profileIdx);
    }

    public List<MovieVO> wishlist(int profileIdx) {
        return myPageMapper.wishlist(profileIdx);
    }

    public List<QuestionVO> questionlist(int profileIdx, int numPerPage, int offset) {
        return myPageMapper.questionlist(profileIdx, numPerPage, offset);
    }

    public QuestionVO questionDetail(String question_idx) {
        return myPageMapper.questionDetail(question_idx);
    }

    public List<ReviewVO> reviewlist(int profileIdx) {
        return myPageMapper.reviewlist(profileIdx);
    }

    public int pwdchk(String userId, String uPwd) {
        return myPageMapper.pwdchk(userId, uPwd);
    }

    public int pwdupdate(String userId, String newPwd) {
        return myPageMapper.pwdupdate(userId, newPwd);
    }

    public int pay(String userId, String uSubs) {
        return myPageMapper.pay(userId, uSubs);
    }

    public int question_count(String profile_idx) {
        return myPageMapper.question_count(profile_idx);
    }
}
