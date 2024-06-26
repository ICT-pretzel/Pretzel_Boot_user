package com.ict.pretzel.ko.vo;

import lombok.Data;

@Data
public class TossVO {
    private String orderId, approvedAt, paymentKey, authorizationHeader, orderName, user_id;
    private int amount;
}
