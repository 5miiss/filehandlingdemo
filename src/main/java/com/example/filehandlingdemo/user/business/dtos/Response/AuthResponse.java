package com.example.filehandlingdemo.user.business.dtos.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthResponse {
    private String message;
    private boolean status;
    private int code;
    private Object data;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime date = LocalDateTime.now();
}