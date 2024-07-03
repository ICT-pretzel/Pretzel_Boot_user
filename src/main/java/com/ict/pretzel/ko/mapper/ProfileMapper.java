package com.ict.pretzel.ko.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.pretzel.vo.ProfileVO;

@Mapper
public interface ProfileMapper {
    
    List<ProfileVO> profile_list(@Param("user_id") String user_id);

    ProfileVO profile_detail(@Param("profile_idx") String profile_idx);

    int profile_insert(ProfileVO profile);
    
    int profile_update(ProfileVO profile);

}
