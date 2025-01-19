package com.flab.jangteoapi.mypage.exception;

public class InsufficientDepositException extends RuntimeException {

    public InsufficientDepositException(String message) {
        super(message);
    }
}
