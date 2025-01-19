package com.flab.jangteoapi.auction.exception;

public class BidNotFoundException extends RuntimeException {

    public BidNotFoundException(String message) {
        super(message);
    }
}
