package com.ict.pretzel.ko.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.UserVO;

@Mapper
public interface UserMapper {

    UserVO login(String user_id) ;
    
    int join(UserVO user);
}
