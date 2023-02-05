package com.starzplay.video.payment.validator;


import java.util.ArrayList;


public class BindingException extends RuntimeException {

    private ArrayList<String> errors = new ArrayList<>();

    public void addPaymentException(String innerError) {
        errors.add(innerError);
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }

    public BindingException() {
        super();
    }

}
