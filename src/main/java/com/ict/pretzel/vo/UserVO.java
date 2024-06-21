package com.ict.pretzel.vo;

import java.util.List;
import java.util.StringJoiner;

import lombok.Data;

@Data
public class UserVO {
    private String user_idx, user_id, pwd, name, email, birth, gender, subs, regdate,  
                last_login, status, admin_id;
    private List<String> like_thema;

      public String getLike_themaAsString() {
        if (like_thema == null || like_thema.isEmpty()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner(",");
        for (String thema : like_thema) {
            joiner.add(thema);
        }
        return joiner.toString();
    }
}
