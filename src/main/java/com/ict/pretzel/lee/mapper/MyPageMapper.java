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

    UserVO getUserDetail(@Param("user_id") String userId);

    List<MovieVO> getWatchList(@Param("profile_idx") int profileIdx);

    List<WishVO> getWishList(@Param("profile_idx") int profileIdx);

    List<QuestionVO> getQuestionList(@Param("profile_idx") int profileIdx);

    List<ReviewVO> getReviewList(@Param("profile_idx") int profileIdx);

    int checkPassword(@Param("user_id") String userId, @Param("u_pwd") String uPwd);

    int updatePassword(@Param("user_id") String userId, @Param("new_pwd") String newPwd);

    int pay(@Param("user_id") String userId, @Param("u_subs") String uSubs);
}
