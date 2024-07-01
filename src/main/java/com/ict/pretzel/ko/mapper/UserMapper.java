package com.ict.pretzel.ko.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.pretzel.vo.UserVO;

@Mapper
public interface UserMapper {

    UserVO login(@Param("user_id") String user_id);
    
    int id_check(@Param("user_id") String user_id);
    
    int email_check(@Param("email") String email);

    int join(UserVO user);

    String find_id(UserVO user);
    
    String find_pwd(UserVO user);

    int pwd_update(UserVO user);

    UserVO findUserByEmail(@Param("email") String email);

    void insertUser(UserVO uvo);

    void updateUser(UserVO uvo);

}
