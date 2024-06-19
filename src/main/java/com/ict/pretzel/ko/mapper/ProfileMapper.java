package com.ict.pretzel.ko.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.pretzel.vo.ProfileVO;

@Mapper
public interface ProfileMapper {
    
    List<ProfileVO> profile_list(String user_id);

    ProfileVO profile_detail(String profile_idx);

    int profile_insert(ProfileVO profile);

}
