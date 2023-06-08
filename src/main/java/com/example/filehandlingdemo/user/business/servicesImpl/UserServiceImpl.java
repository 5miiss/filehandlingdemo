package com.example.filehandlingdemo.user.business.servicesImpl;


import com.example.filehandlingdemo.exceptions.ObjectNotFoundException;
import com.example.filehandlingdemo.user.business.dtos.Request.UserDto;
import com.example.filehandlingdemo.user.business.dtos.Request.UserRoleDto;
import com.example.filehandlingdemo.user.business.dtos.Response.UserResponseDto;
import com.example.filehandlingdemo.user.business.mappers.UserMapper;
import com.example.filehandlingdemo.user.business.services.UserService;
import com.example.filehandlingdemo.user.persistence.entities.Role;
import com.example.filehandlingdemo.user.persistence.entities.User;
import com.example.filehandlingdemo.user.persistence.repositories.RoleRepo;
import com.example.filehandlingdemo.user.persistence.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final JwtService jwtService;



    @Override
    public UserResponseDto getUser(String username) {
        User user = userRepo.findUserByUsernameAndDeletedIsFalse(username).orElseThrow(()-> new ObjectNotFoundException("UserName Doesn't Exist"));
        return new UserResponseDto("User Found",true ,200,user);
    }
    @Override
    public UserResponseDto getUserById(Long id) {
          User user = userRepo.findUserByIdAndDeletedIsFalse(id).orElseThrow(()-> new ObjectNotFoundException("User Doesn't Exist"));
          return new UserResponseDto("User Found",true ,200,user);
    }

    @Override
    public UserResponseDto addRoleToUser(UserRoleDto userRoleDto) {
        User user= userRepo.findUserByUsernameAndDeletedIsFalse(userRoleDto.getUsername()).orElseThrow(()->new ObjectNotFoundException("UserName Doesn't Exist"));
        if(!Objects.equals(user.getId(), userRoleDto.getUserId())){
            throw new IllegalArgumentException("your Data of User Not Compatible with Database");
        }
        Role role = new Role();

        if(!roleRepo.existsRoleByName("ROLE_"+userRoleDto.getRoleName())){
            role.setName("ROLE_"+userRoleDto.getRoleName());
            role = roleRepo.saveAndFlush(role);
        }else {
            role = roleRepo.findByName("ROLE_"+userRoleDto.getRoleName()).orElseThrow();
        }
        user.getRoles().add(role);
        userRepo.saveAndFlush(user);
        return new UserResponseDto("Role Added to User Successfully",true ,200,user);
    }

    @Override
    public UserResponseDto softDeleteUser(UserDto userDto) {
        User user = userRepo.findById(userDto.getId()).orElseThrow(()-> new ObjectNotFoundException("User doesn't exist"));
        if(!user.getUsername().equals(userDto.getUsername())){
            throw new IllegalArgumentException("your Data of User Not Compatible with Database");
        }
        user.setDeleted(true);
        userRepo.save(user);
        return new UserResponseDto("User Deleted Successfully",true , 200 , user );
    }

    @Override
    public UserResponseDto getAll() {
        return new UserResponseDto("All users" , true , 200 ,userMapper.usersToUsersDto(userRepo.findAllByDeletedIsFalse())) ;
    }

    @Override
    public UserResponseDto activateUser(Long id) {
        User user = userRepo.findUserByIdAndDeletedIsFalse(id).orElseThrow(()-> new ObjectNotFoundException("User doesn't exist"));
        if(user.isActive()){
            return new UserResponseDto("User Already Activated" , true , 200 ,user);
        }
        user.setActive(true);
        userRepo.save(user);


        return new UserResponseDto("User Activated Successfully" , true , 200 ,user) ;

    }

    @Override
    public UserResponseDto deActivateUser(Long id) {
        User user = userRepo.findUserByIdAndDeletedIsFalse(id).orElseThrow(()-> new ObjectNotFoundException("User doesn't exist"));
        if(!user.isActive()){
            return new UserResponseDto("User Already deActivated" , true , 200 ,user);
        }
        user.setActive(false);
        userRepo.save(user);

        return new UserResponseDto("User deActivated Successfully" , true , 200 ,user) ;

    }

    @Override
    public UserResponseDto updateOneSignal(String newOSId, HttpServletRequest request) {
        User user = getUSerByRequest(request);
        user.setOneSignalId(newOSId);
        userRepo.saveAndFlush(user);

        return new UserResponseDto("OneSignal Updated Successfully" , true , 200 ,Map.of("userName",user.getUsername() , "oneSignalId",newOSId));
    }

//    @Override
//    public UserResponseDto getByDriverId(Long driverId) {
//        User user = userRepo.findByDriver_DriverId(driverId).orElseThrow(()-> new ObjectNotFoundException("User doesn't exist"));
//        return  new UserResponseDto("User Found" , true , 200 ,userMapper.userToUserDto(user));
//    }
    @Override
    public UserResponseDto getByToken(HttpServletRequest request) {

        User user = getUSerByRequest(request);
        return  new UserResponseDto("User Found" , true , 200 ,user);
    }

    private User getUSerByRequest(HttpServletRequest request){
        String UUID = jwtService.extractUUIDFromRequest(request);
        return userRepo.findByRef(UUID).orElseThrow(()-> new ObjectNotFoundException("User doesn't exist"));
    }

    @Override
    public UserResponseDto getOneSignal(HttpServletRequest request) {
        User user = getUSerByRequest(request);
        return  new UserResponseDto("User OneSignal" , true , 200 ,user.getOneSignalId());
    }
}
