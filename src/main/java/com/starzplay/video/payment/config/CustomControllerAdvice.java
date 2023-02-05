package com.starzplay.video.payment.config;


import com.starzplay.video.payment.exception.PaymentException;
import com.starzplay.video.payment.exception.PaymentExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PaymentException handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = "msg.error.badRequest.database";
//        if (e.getCause() instanceof ConstraintViolationException constraintViolationException) {
//                message = "msg.error.badRequest.hasParent";
//        }
        log.info("DataIntegrityViolationException : ", e);
        int code = 8888;
        String literal = message;
        return new PaymentException(PaymentExceptionType.DEFAULT_EXCEPTION);
    }

}
