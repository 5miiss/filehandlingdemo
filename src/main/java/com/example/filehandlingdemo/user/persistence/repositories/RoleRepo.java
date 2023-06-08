package com.example.filehandlingdemo.user.persistence.repositories;

import com.example.filehandlingdemo.user.persistence.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {


    Optional<Role> findByName(String roleName);
    boolean existsRoleByName(String name);
}
