package com.example.filehandlingdemo.user.business.servicesImpl;

import com.example.filehandlingdemo.exceptions.ObjectNotFoundException;
import com.example.filehandlingdemo.user.business.dtos.Response.UserResponseDto;
import com.example.filehandlingdemo.user.business.mappers.RoleMapper;
import com.example.filehandlingdemo.user.business.services.RoleService;
import com.example.filehandlingdemo.user.persistence.entities.Role;
import com.example.filehandlingdemo.user.persistence.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final RoleMapper roleMapper;

    @Override
    public UserResponseDto saveRole(String roleName) {
        Role role = roleRepo.findByName(roleName).orElse(null);
        if(role != null){
            return new  UserResponseDto("Role Already Exist",true , 200 ,role);
        }
        role = new Role("ROLE_"+roleName);
        roleRepo.save(role);
        return new UserResponseDto("New Role Added Successfully",true , 200 ,role);
    }

    @Override
    public UserResponseDto getAll() {
        return new UserResponseDto("All Roles",true,200,roleMapper.roleName(roleRepo.findAll())) ;
    }

    @Override
    public UserResponseDto getById(Long id) {
        return  new UserResponseDto("Role Found",true,200,roleRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException("Role Not Found")));
    }
}
