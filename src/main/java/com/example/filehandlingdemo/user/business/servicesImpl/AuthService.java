package com.example.filehandlingdemo.user.business.servicesImpl;



import com.example.filehandlingdemo.exceptions.ObjectNotFoundException;
import com.example.filehandlingdemo.user.business.dtos.Request.AuthRequest;
import com.example.filehandlingdemo.user.business.dtos.Request.PhoneRegisterDto;
import com.example.filehandlingdemo.user.business.dtos.Request.UserRegistrationDto;
import com.example.filehandlingdemo.user.business.dtos.Response.AuthResponse;
import com.example.filehandlingdemo.user.business.mappers.UserMapper;
import com.example.filehandlingdemo.user.persistence.entities.Role;
import com.example.filehandlingdemo.user.persistence.entities.User;
import com.example.filehandlingdemo.user.persistence.repositories.RoleRepo;
import com.example.filehandlingdemo.user.persistence.repositories.UserRepo;
import com.example.filehandlingdemo.utils.LoginUtil;
import com.example.filehandlingdemo.utils.Validators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;

    private final LoginUtil loginUtil;
    private final Validators validators;
    private final PasswordEncoder passwordEncoder;
    private  final  JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserRegistrationDto request) {
        if(userRepo.existsByUsername(request.getUsername())){
            return AuthResponse.builder()
                    .message("Username already exist")
                    .status(false)
                    .code(2001)
                    .build();
        }
        User user = userMapper.userRegisterDtoToUser(request);
        Role role = roleRepo.findByName("ROLE_"+request.getRole().toUpperCase()).orElse(null);
        if(role == null){
           role = roleRepo.save(new Role("ROLE_"+request.getRole().toUpperCase()));
        }
        user.setRoles(Set.of(role));

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);


        return AuthResponse.builder()
                .message("Registered Successfully")
                .status(true)
                .code(200)
//                .data(userMapper.userToUserDto(user))
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    public AuthResponse registerWithPhone( PhoneRegisterDto validAuthDto) {
//        if (validators.hasValidationErrors(validAuthDto)){
//            return null;
//        }
        if(userRepo.existsByUsername(validAuthDto.getPhone())){
            return AuthResponse.builder()
                    .message("Username already exist")
                    .status(false)
                    .code(2001)
                    .build();
        }
//        User user = driverService.addUserDriver(validAuthDto);
//        if (user == null){
//            return AuthResponse.builder()
//                    .message("has validation errors")
//                    .status(false)
//                    .code(204)
//                    .data(null)
//
//                    .build();
//        }
//        Map<String, String> refNo = new HashMap<>();
//        refNo.put("userRefNo",user.getRef());

//        var jwtToken = jwtService.generateToken(user);
//        var jwtRefreshToken = jwtService.generateRefreshToken(user);


        return AuthResponse.builder()
                .message("Registered Successfully")
                .status(true)
                .code(200)
//                .data(refNo)
//                .accessToken(jwtToken)
//                .refreshToken(jwtRefreshToken)
                .build();
    }


    public AuthResponse login(AuthRequest request) {
        String message ;
        boolean status = true;
        if(!userRepo.existsByUsernameAndDeletedIsFalse(request.getUserName())){
            return AuthResponse.builder()
                    .message("Username Doesn't exist!")
                    .status(false)
                    .code(2002)
                    .build();
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );


        User user = userRepo.findUserByUsernameAndDeletedIsFalse(request.getUserName()).orElseThrow(()-> new ObjectNotFoundException("User Not Exist"));
        var jwtToken = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);
        message = "User "+request.getUserName()+" LoggedIn Successfully";



        return AuthResponse.builder()
                .status(status)
                .message(message)
                .code(200)
//                .data(userMapper.userToUserDto(user))
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }
    public AuthResponse loginWithPhone(String phone) {
        String message ;
        boolean status = true;
//        Driver driver = driverRepo.findByUser_UsernameAndUser_DeletedFalse(phone)
//                .orElseThrow(()->new ObjectNotFoundException("no driver found with that phone"));
//        if (driver.getDriverStatus() == DriverStatus.DISABLED ||driver.getDriverStatus() == DriverStatus.SUSPENDED){
//            return AuthResponse.builder()
//                    .status(false)
//                    .message("driver status is suspended or disabled")
//                    .code(loginUtil.getStatusCode(driver.getDriverStatus()))
//                    .data(null)
//                    .build();
//        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        phone,
                        ""
                )
        );


        User user = userRepo.findUserByUsernameAndDeletedIsFalse(phone).orElseThrow(()-> new ObjectNotFoundException("User Not Found"));
        var jwtToken = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);
        message = "User "+user.getUsername()+" LoggedIn Successfully";



        return AuthResponse.builder()
                .status(status)
                .message(message)
                .code(200)
//                .data(userMapper.userToUserRespDto(user))
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }
}