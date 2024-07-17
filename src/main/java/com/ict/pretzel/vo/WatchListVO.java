package com.ict.pretzel.vo;

import lombok.Data;

@Data
public class WatchListVO {
    private String movie_idx, movie_id, tmdb_title, korea_title, english_title, thema,
    synopsis, movie_url, subtitle_url,trailer_url,poster_url,backdrop_url,movie_grade,runtime,
    release_date,status, watch_date;
    ;
}
