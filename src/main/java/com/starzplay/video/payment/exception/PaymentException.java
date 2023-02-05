package com.starzplay.video.payment.exception;

import java.util.function.Supplier;

public class PaymentException extends StarzPlayException implements Supplier<PaymentException> {

    @Override
    public PaymentException get() {
        return this;
    }

    public PaymentException(int code, String messageEn, String messageAr, String messageUr) {
        this.setCode(code);
        this.setMessageEn(messageEn);
        this.setMessageAr(messageAr);
        this.setMessageUr(messageUr);
    }

    public PaymentException(PaymentExceptionType exceptionType) {
        this(exceptionType.getErrorCode(), exceptionType.getErrorMessageEn(), exceptionType.getErrorMessageAr(), exceptionType.getErrorMessageUr());
    }

}
