package com.starzplay.video.payment.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarzPlayException extends Exception {

    private int code;
    private String messageEn;
    private String messageAr;
    private String messageUr;

}
