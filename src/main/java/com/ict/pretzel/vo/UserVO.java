package com.ict.pretzel.vo;

import lombok.Data;

@Data
public class UserVO {
    private String user_idx, user_id, pwd, name, email, birth, gender, subs, regdate,  
                like_thema, last_login, status, admin_id;
}
