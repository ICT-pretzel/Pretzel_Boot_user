package com.ict.pretzel.ko.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.pretzel.ko.vo.TossVO;
import java.util.List;

@Mapper
public interface TossMapper {

    void toss_insert(TossVO toss);

    void subs_update(TossVO toss);

    TossVO toss_detail(@Param("toss_idx") String toss_idx);

    int toss_cancel(TossVO toss);

    List<TossVO> toss_list();
}
