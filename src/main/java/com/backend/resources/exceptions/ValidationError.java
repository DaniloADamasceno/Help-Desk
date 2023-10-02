package com.backend.resources.exceptions;

import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandardError {

    private List<FieldMessage> errorsListValidation = new ArrayList<>();

    //? -----------------------------------------   Constructor  -------------------------------------------------------
    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error,message, path);
    }

    //? -----------------------------------------   Getters and Setters  -----------------------------------------------


    public List<FieldMessage> getErrorsListValidation() {
        return errorsListValidation;
    }

    public void addErrors(String fildName, String message) {
        this.errorsListValidation.add(new FieldMessage(fildName, message));

    }
}
