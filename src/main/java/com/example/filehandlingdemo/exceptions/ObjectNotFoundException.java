package com.example.filehandlingdemo.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}