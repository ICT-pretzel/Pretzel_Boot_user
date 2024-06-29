package com.ict.pretzel.ko.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.UserVO;
import com.ict.pretzel.ko.vo.TossVO;

@Mapper
public interface UserMapper {

    UserVO login(String user_id) ;
    
    int id_check(String user_id);
    
    int email_check(String email);

    int join(UserVO user);

    String find_id(UserVO user);
    
    String find_pwd(UserVO user);

    int pwd_update(UserVO user);

    UserVO findUserByEmail(String email);

    void insertUser(UserVO uvo);

    void updateUser(UserVO uvo);

    int toss_insert(TossVO toss);

    int subs_update(TossVO toss);
}
