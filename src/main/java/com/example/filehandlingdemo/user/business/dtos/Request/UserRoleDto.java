package com.example.filehandlingdemo.user.business.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    @Min(value = 1, message = "Invalid userId: Can not Equals to zero or Less than zero")
    private Long userId;
    @NotNull(message = "username Field cannot be Null")
    @NotBlank(message = "username Field cannot be Empty")
    @Size(min = 2 ,message = "UserName must be 2 chars or more")
    private String username;
    @NotBlank(message = "roleName Field cannot be Empty")
    @NotNull(message = "roleNameField cannot be Null")
    private String roleName;

}
