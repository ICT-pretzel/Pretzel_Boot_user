package com.ict.pretzel.ko.vo;

import lombok.Data;

@Data
public class TokenVO {
    private String user_id, profile_idx, token, name, img_name, subs;

    public TokenVO() {
    }
    
    public TokenVO(String token) {
        this.token = token;
    }

}
