package com.example.filehandlingdemo.user.business.mappers;


import com.example.filehandlingdemo.user.business.dtos.Request.UserDto;
import com.example.filehandlingdemo.user.business.dtos.Request.UserRegistrationDto;
import com.example.filehandlingdemo.user.persistence.entities.User;
import org.mapstruct.Mapper;

import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public abstract class UserMapper {

    public abstract Set<UserDto> usersToUsersDto(Set<User> users);
    public abstract User userDtoToUser(UserDto userDto);

    public abstract User userRegisterDtoToUser(UserRegistrationDto userRegistrationDto);
    public abstract UserRegistrationDto userToUserRegisterDto(User user);

}
