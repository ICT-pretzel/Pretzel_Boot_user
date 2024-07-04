package com.ict.pretzel.vo;

import lombok.Data;

@Data
public class MovieReviewVO {
    private String review_idx,movie_idx,profile_idx,rating,content,regdate,status,admin_id,name,img_name,user_id;
}
