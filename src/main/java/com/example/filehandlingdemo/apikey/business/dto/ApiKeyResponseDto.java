package com.example.filehandlingdemo.apikey.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApiKeyResponseDto {

    private String message;
    private boolean status;
    private int code;
    private Object ApiKeys;
    LocalDateTime date = LocalDateTime.now();

    public ApiKeyResponseDto(String message, boolean status, int code, Object apiKeys) {
        this.message = message;
        this.status = status;
        this.code = code;
        ApiKeys = apiKeys;
    }
}
