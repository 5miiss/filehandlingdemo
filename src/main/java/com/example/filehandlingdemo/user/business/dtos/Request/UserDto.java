package com.example.filehandlingdemo.user.business.dtos.Request;

import com.example.filehandlingdemo.user.persistence.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Min(value = 1, message = "Invalid id: Can not Equals to zero or Less than zero")
    private Long id;
    @NotNull(message = "username Field cannot be Null")
    @NotBlank(message = "username Field cannot be Empty")
    @Size(min = 2 ,message = "username must be 2 chars or more")
    private String username;
    private UserDriverDto driver;
    private String oneSignalId="N/A";
    private String lang="ar";
    private boolean active = true;
    private boolean verified = false;
    private Set<Role> roles = new HashSet<>();

}
