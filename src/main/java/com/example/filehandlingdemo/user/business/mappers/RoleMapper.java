package com.example.filehandlingdemo.user.business.mappers;

import com.example.filehandlingdemo.user.persistence.entities.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoleMapper {
    public List<String> roleName(List<Role> roles){
        return roles.stream().map(r -> r.getName().substring(5)).collect(Collectors.toList());
    }
}
