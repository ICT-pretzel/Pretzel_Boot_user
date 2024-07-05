package com.ict.pretzel.ko.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.ko.vo.TossVO;

@Mapper
public interface TossMapper {

    void toss_insert(TossVO toss);

    void subs_update(TossVO toss);
}
