package com.starzplay.video.payment.enums;


import lombok.Getter;

@Getter
public enum Currency {
    USD(1),
    SAR(2);

    private final Integer value;

    Currency(final Integer value) {
        this.value = value;
    }

    public static Currency find(String type) {
        return valueOf(type.toUpperCase());
    }
}
