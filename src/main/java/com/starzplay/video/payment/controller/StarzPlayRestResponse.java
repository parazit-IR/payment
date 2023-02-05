package com.starzplay.video.payment.controller;


import lombok.Data;

import java.util.List;

@Data
public class StarzPlayRestResponse<T> {

    private Integer errorCode;
    private String errorMessage;
    private String ArabicErrorMessage;
    private String UrduErrorMessage;
    private List<String> errors;
    private T data;

}
