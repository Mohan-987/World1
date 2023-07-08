package com.world.in.exception;

public class CountryLanguageNotFoundException extends RuntimeException{
    public CountryLanguageNotFoundException(String message) {
        super(message);
    }
}
