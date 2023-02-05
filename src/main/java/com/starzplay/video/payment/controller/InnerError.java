package com.starzplay.video.payment.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InnerError {

    private int code;
    private String messageEn;
    private String messageAr;
    private String messageUr;
}
