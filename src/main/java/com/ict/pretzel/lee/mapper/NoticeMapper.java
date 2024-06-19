package com.ict.pretzel.lee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ict.pretzel.vo.FaqVO;
import com.ict.pretzel.vo.NoticeVO;

@Mapper
public interface NoticeMapper {

    List<NoticeVO> getNoticeList();

    List<FaqVO> getFaqList();

    int addQuestion(@Param("token") String token, @Param("question_title") String question_title, @Param("question_content") String question_content);
}
