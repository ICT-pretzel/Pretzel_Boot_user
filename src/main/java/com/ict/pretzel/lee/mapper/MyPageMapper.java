package com.ict.pretzel.lee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ict.pretzel.vo.MovieVO;
import com.ict.pretzel.vo.QuestionVO;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.UserVO;
import com.ict.pretzel.vo.WatchListVO;
import com.ict.pretzel.vo.WishVO;

@Mapper
public interface MyPageMapper {

    UserVO userdetail(@Param("user_id") String userId);

    List<WatchListVO> watchlist(@Param("profile_idx") int profileIdx);

    List<MovieVO> wishlist(@Param("profile_idx") int profileIdx);

    List<QuestionVO> questionlist(@Param("profile_idx") int profileIdx);

    QuestionVO questionDetail(@Param("question_idx") String question_idx);

    List<ReviewVO> reviewlist(@Param("profile_idx") int profileIdx);

    int pwdchk(@Param("user_id") String userId, @Param("u_pwd") String uPwd);

    int pwdupdate(@Param("user_id") String userId, @Param("new_pwd") String newPwd);

    int pay(@Param("user_id") String userId, @Param("u_subs") String uSubs);
}
