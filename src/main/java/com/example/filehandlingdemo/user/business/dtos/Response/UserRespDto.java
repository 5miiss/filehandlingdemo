package com.example.filehandlingdemo.user.business.dtos.Response;

import com.example.filehandlingdemo.user.persistence.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDto {

    private String username;
    private UserDriverRespDto driver;
    private String oneSignalId="N/A";
    private String lang="ar";
    private boolean active = true;
    private boolean verified = false;
    private Set<Role> roles = new HashSet<>();
}
