package com.ict.pretzel.ko.vo;

import lombok.Data;

@Data
public class TossVO {
    private String toss_idx, orderId, approvedAt, paymentKey, authorizationHeader, orderName, 
                    cancelReason, canceledAt, user_id, subs_value;
    private int amount, cancelAmount;
}
