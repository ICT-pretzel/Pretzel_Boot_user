package com.ict.pretzel.lee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ict.pretzel.vo.ReviewVO;
import com.ict.pretzel.vo.ReportVO;

@Mapper
public interface MovieDetailMapper {

    int addReview(@Param("review") ReviewVO review); // 리뷰 추가 메서드

    int deleteReview(@Param("profileIdx") String profileIdx, @Param("reviewIdx") String reviewIdx); // 리뷰 삭제 메서드

    int addReport(@Param("report") ReportVO report); // 신고 추가 메서드

    int addWish(@Param("profileIdx") String profileIdx, @Param("movieIdx") String movieIdx); // 찜 추가 메서드

    int deleteWish(@Param("profileIdx") String profileIdx, @Param("movieIdx") String movieIdx); // 찜 삭제 메서드
}
