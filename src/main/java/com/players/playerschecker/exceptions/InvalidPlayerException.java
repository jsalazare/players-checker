package com.players.playerschecker.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class InvalidPlayerException extends Exception {
    List<ObjectError> errorList;


    public InvalidPlayerException(List<ObjectError> errorList) {
        this.errorList = errorList;
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ObjectError> errorList) {
        this.errorList = errorList;
    }
}
