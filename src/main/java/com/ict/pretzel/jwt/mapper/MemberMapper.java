package com.ict.pretzel.jwt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.pretzel.jwt.vo.MemberVO;
import java.util.List;

@Mapper
public interface MemberMapper {
    MemberVO selectMember(@Param("id") String id) ;
}
