package com.starzplay.video.payment.enums;


import lombok.Getter;

@Getter
public enum PaymentType {
    CREDIT_CARD(1),
    VOUCHER(2),
    MOBILE_CARRIER(3);

    private final Integer value;

    PaymentType(final Integer value) {
        this.value = value;
    }

    public static PaymentType find(String type) {
        return valueOf(type.toUpperCase());
    }
}