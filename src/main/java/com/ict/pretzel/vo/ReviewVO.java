package com.ict.pretzel.vo;

import lombok.Data;

@Data
public class ReviewVO {
    private String review_idx;
    private String movie_idx;
    private String profile_idx;
    private String rating;
    private String content;
    private String regdate;
    private String status;
    private String admin_id;
    private MovieVO movie;
}
