package com.example.filehandlingdemo.user.business.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotNull(message = "UserName Field cannot be Null")
    @NotBlank(message = "userName Field cannot be Empty")
    @Size(min = 2 ,message = "userName must be 2 chars or more")
    private  String userName;

    @Size(min = 4 , message = "password length must be more than 4 or more")
    private String password;
}