package com.ict.pretzel.vo;

import lombok.Data;

@Data
public class QuestionVO {
    private String question_idx, profile_idx, title, content, answer, regdate, ansdate, status, admin_id;
}
