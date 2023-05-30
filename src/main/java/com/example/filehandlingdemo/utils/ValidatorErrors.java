package com.example.filehandlingdemo.utils;

import java.io.Serializable;

import lombok.*;
@Data
@AllArgsConstructor
public class ValidatorErrors implements Serializable {
    private int errorCode;
    private String errorMessage; 
    
}
