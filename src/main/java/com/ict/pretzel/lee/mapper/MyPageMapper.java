package com.ict.pretzel.lee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.UserVO;
import com.ict.pretzel.vo.WishVO;

@Mapper
public interface MyPageMapper {

    UserVO getUserDetail(@Param("token") String token);

    List<MovieVO> getWatchList(@Param("token") String token);

    List<WishVO> getWishList(@Param("token") String token);

    List<QuestionVO> getQuestionList(@Param("token") String token);

    List<ReviewVO> getReviewList(@Param("token") String token);

    int checkPassword(@Param("token") String token, @Param("u_pwd") String u_pwd);

    int updatePassword(@Param("token") String token, @Param("new_pwd") String new_pwd);

    int pay(@Param("price") String price, @Param("select_lev") String select_lev, @Param("token") String token);
}