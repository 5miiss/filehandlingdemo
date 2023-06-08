package com.example.filehandlingdemo.user.business.services;

import com.example.filehandlingdemo.user.business.dtos.Response.UserResponseDto;

public interface RoleService {
    UserResponseDto saveRole(String roleName) ;
    UserResponseDto getAll();

    UserResponseDto getById(Long id);
}
