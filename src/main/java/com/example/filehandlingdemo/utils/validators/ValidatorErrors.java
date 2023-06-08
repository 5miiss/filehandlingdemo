package com.example.filehandlingdemo.utils.validators;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ValidatorErrors implements Serializable {
    private int errorCode;
    private String errorMessage;

    @Override
    public String toString() {
        return errorCode + ":" + errorMessage + '\n';
    }
}
