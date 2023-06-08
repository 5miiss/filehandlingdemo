package com.example.filehandlingdemo.user.controllers;

import com.example.filehandlingdemo.user.business.dtos.Request.*;
import com.example.filehandlingdemo.user.business.dtos.Response.AuthResponse;
import com.example.filehandlingdemo.user.business.dtos.Response.UserResponseDto;
import com.example.filehandlingdemo.user.business.services.UserService;
import com.example.filehandlingdemo.user.business.servicesImpl.AuthService;
import com.example.filehandlingdemo.user.business.servicesImpl.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/register")
    public AuthResponse Register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return authService.register(userRegistrationDto);
    }

    @PostMapping("/registerWithPhone")
    public AuthResponse RegisterWithPhone(@RequestBody @Valid PhoneRegisterDto validAuthDto) {

        log.info("Request {} ", validAuthDto);
        AuthResponse resp = authService.registerWithPhone(validAuthDto);
        log.info("Resp {} ", resp);
        return resp;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/loginWithPhone/{phone}")
    public AuthResponse loginWithPhone(@PathVariable String phone) {
        return authService.loginWithPhone(phone);
    }

    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/auth")
    @RolesAllowed("DRIVER")
    public String test() {
        return "user";
    }

    @GetMapping("/{username}")
    public UserResponseDto getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @GetMapping("/getById/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //    @GetMapping("/getByDriverId/{id}")
//    public UserResponseDto getUserByDriverId(@PathVariable Long id){
//       return  userService.getByDriverId(id);
//    }
    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/getByToken")
    public UserResponseDto getUserByToken(HttpServletRequest request) {
        return userService.getByToken(request);
    }

    @PostMapping("/addRole")
    public UserResponseDto addRoleToUser(@RequestBody @Valid UserRoleDto userRoleDto) {
        return userService.addRoleToUser(userRoleDto);
    }


    @DeleteMapping()
    public UserResponseDto delete(@RequestBody @Valid UserDto userDto) {
        return userService.softDeleteUser(userDto);
    }
    @SecurityRequirement(name = "BearerAuth")
    @RolesAllowed({"FAC","DRIVER"})
    @GetMapping("/all")
    public UserResponseDto getAll() {
        return userService.getAll();
    }

    @PutMapping("/activate/{id}")
    public UserResponseDto activateUser(@PathVariable Long id) {
        return userService.activateUser(id);
    }

    @PutMapping("/deactivate/{id}")
    public UserResponseDto deActivateUser(@PathVariable Long id) {
        return userService.deActivateUser(id);
    }
    @SecurityRequirement(name = "BearerAuth")
    @PutMapping("/OneSignal/{newOSId}")
    public UserResponseDto updateOneSignal(@PathVariable String newOSId , HttpServletRequest request){
        return userService.updateOneSignal(newOSId , request);
    }
    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/OneSignal")
    public UserResponseDto getOneSignal( HttpServletRequest request){
        return userService.getOneSignal(request);
    }

    @SecurityRequirement(name = "BearerAuth")

    @PostMapping("/testToken")
    public String testToken(HttpServletRequest request) {
        JwtService jwtService = new JwtService();

        return jwtService.extractUUIDFromRequest(request);
    }


}
