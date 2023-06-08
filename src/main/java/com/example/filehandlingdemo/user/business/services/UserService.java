package com.example.filehandlingdemo.user.business.services;


import com.example.filehandlingdemo.user.business.dtos.Request.UserDto;
import com.example.filehandlingdemo.user.business.dtos.Request.UserRoleDto;
import com.example.filehandlingdemo.user.business.dtos.Response.UserResponseDto;

import javax.servlet.http.HttpServletRequest;


public interface UserService {
    UserResponseDto getUser(String username);

    UserResponseDto addRoleToUser(UserRoleDto userRoleDto);

    UserResponseDto softDeleteUser(UserDto userDto);

    UserResponseDto getAll();

    UserResponseDto getUserById(Long id);

    UserResponseDto activateUser(Long id);

    UserResponseDto deActivateUser(Long id);

    UserResponseDto updateOneSignal(String newOSId , HttpServletRequest request);

//    UserResponseDto getByDriverId(Long driverId);

    UserResponseDto getByToken(HttpServletRequest request);

    UserResponseDto getOneSignal(HttpServletRequest request);
}