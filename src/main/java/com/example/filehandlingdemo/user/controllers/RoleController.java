package com.example.filehandlingdemo.user.controllers;

import com.example.filehandlingdemo.user.business.dtos.Response.UserResponseDto;
import com.example.filehandlingdemo.user.business.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleService roleService;
    @GetMapping("/all")
    public UserResponseDto getAll(){
        return roleService.getAll();
    }
    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id){
        return roleService.getById(id);
    }
    @PostMapping("/{roleName}")
    public UserResponseDto addNewRole(@PathVariable String roleName){
        return roleService.saveRole(roleName);
    }

}
