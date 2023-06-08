package com.example.filehandlingdemo.apikey.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiKeyDto {

    private String apiKey;
    private String refNo;
    private boolean isExpired;
    private String name;


}
