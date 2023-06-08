package com.example.filehandlingdemo.user.business.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    @NotNull(message = "username Field cannot be Null")
    @NotBlank(message = "username Field cannot be Empty")
    @Size(min = 2 , message = "username must be 2 chars or more")
    private String username;
    @Size(min =  4 , message = "password length must be more than 4 or more")
    private String password;
    @NotNull(message = "Enter the role name")
    private String role;
    private String phone;
    private String oneSignalId="N/A";
    private String lang="ar";

}
