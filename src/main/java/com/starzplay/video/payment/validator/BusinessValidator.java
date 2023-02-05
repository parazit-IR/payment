package com.starzplay.video.payment.validator;


import com.starzplay.video.payment.exception.PaymentException;

public interface BusinessValidator<T> {
    void validate(T t) throws PaymentException;
}
