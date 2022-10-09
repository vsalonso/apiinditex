package com.victor.apiinditex.infrastruture.api.exception;


public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(final String message) {
        super(message);
    }
}
