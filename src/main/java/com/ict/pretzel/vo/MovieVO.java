package com.ict.pretzel.vo;

import lombok.Data;

@Data
public class MovieVO {
    private String movie_idx, movie_id, tmdb_title, korea_title, english_title, thema,
    synopsis, movie_url, storage_name,trailer_url,poster_url,backdrop_url,movie_grade,runtime,
    release_date,status,insert_time,update_time,delete_time,admin_id,watch_stack,synchro,
    w_teen_stack,w_twenty_stack,w_thirty_stack,w_forty_stack,w_fifty_stack,
    m_teen_stack,m_twenty_stack,m_thirty_stack,m_forty_stack,m_fifty_stack,woman_stack,man_stack;
}
