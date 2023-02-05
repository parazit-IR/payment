package com.starzplay.video.payment.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum PaymentExceptionType {

    DEFAULT_EXCEPTION(1, "Default Exception", "", ""),
    PAYMENT_METHOD_NOT_FOUND_EXCEPTION(2, "Payment Method Not Found", "", ""),
    PAYMENT_PLAN_NOT_FOUND_EXCEPTION(3, "Payment Method Not Found", "", ""),
    PAYMENT_METHOD_EXISTS_EXCEPTION(4, "Payment Method Exists", "", ""),
    BAD_REQUEST_EXCEPTION(5, "Bad Request", "", ""),
    PAYMENT_PLANT_IS_NECESSARY(6, "Payment plant is necessary", "", "");



    private int errorCode;
    private String errorMessageEn;
    private String errorMessageAr;
    private String errorMessageUr;

}
