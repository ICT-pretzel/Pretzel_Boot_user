package com.ict.pretzel.jwt.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String id, email, password, created_at, last_login, is_activated ;
}
