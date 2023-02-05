package com.starzplay.video.payment.enums;


import lombok.Getter;

@Getter
public enum Duration {

    Day(1),
    Week(2),
    Month(3);

    private final Integer value;

    Duration(final Integer value) {
        this.value = value;
    }

    public static Duration find(String type) {
        return valueOf(type.toUpperCase());
    }
}
