package com.ict.pretzel.vo;

import lombok.Data;

@Data
public class NoticeVO {
    private String notice_idx, title, content, regdate, status, admin_id;
}
