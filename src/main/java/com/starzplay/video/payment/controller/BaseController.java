package com.starzplay.video.payment.controller;


import com.starzplay.video.payment.exception.PaymentException;
import com.starzplay.video.payment.exception.StarzPlayException;
import com.starzplay.video.payment.validator.BindingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.starzplay.video.payment.exception.PaymentExceptionType.BAD_REQUEST_EXCEPTION;
import static com.starzplay.video.payment.exception.PaymentExceptionType.DEFAULT_EXCEPTION;

@Slf4j
public class BaseController<T> {

    protected ResponseEntity<StarzPlayRestResponse> failure(Exception e) {
        StarzPlayRestResponse starzPlayRestResponse = new StarzPlayRestResponse();
        starzPlayRestResponse.setErrorMessage(e.getMessage());
        StarzPlayException pe = new StarzPlayException();
        if (e instanceof StarzPlayException) {
            pe = (StarzPlayException) e;
            log.error(pe.toString());
        }
        else if (e instanceof BindingException){
            BindingException b =(BindingException) e;
            starzPlayRestResponse.setErrors(b.getErrors());
        }
        else {
            e.printStackTrace();
            log.error(e.getStackTrace().toString());
            pe = new PaymentException(DEFAULT_EXCEPTION);
        }
        starzPlayRestResponse.setErrorCode(pe.getCode());
        starzPlayRestResponse.setErrorMessage(pe.getMessageEn());
        starzPlayRestResponse.setArabicErrorMessage(pe.getMessageAr());
        starzPlayRestResponse.setUrduErrorMessage(pe.getMessageUr());
        ResponseEntity<StarzPlayRestResponse> responseEntity = new ResponseEntity<>(starzPlayRestResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    protected ResponseEntity<StarzPlayRestResponse<T>> success(T result) {
        StarzPlayRestResponse<T> shoppRestResponse = new StarzPlayRestResponse<>();
        shoppRestResponse.setData(result);
        ResponseEntity<StarzPlayRestResponse<T>> responseEntity = new ResponseEntity(result, HttpStatus.OK);
        return responseEntity;
    }

    protected ResponseEntity<StarzPlayRestResponse<T>> success(T result, HttpHeaders httpHeaders) {
        StarzPlayRestResponse<T> shoppRestResponse = new StarzPlayRestResponse();
        shoppRestResponse.setData(result);
        ResponseEntity<StarzPlayRestResponse<T>> responseEntity = new ResponseEntity(result, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

}

