package com.starzplay.video.payment.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    WARNING(1),
    ERROR(2);

    private final Integer value;

    ExceptionCode(final Integer value) {
        this.value = value;
    }

    public static ExceptionCode find(String type) {
        return valueOf(type.toUpperCase());
    }
}


