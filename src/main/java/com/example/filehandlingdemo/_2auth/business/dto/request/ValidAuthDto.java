package com.example.filehandlingdemo._2auth.business.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidAuthDto {
    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
//    @Pattern(regexp = "^\\d{11}$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Invalid OTP number: Empty number")
    @NotNull(message = "Invalid OTP number: Number is NULL")
    private String otp;
}
