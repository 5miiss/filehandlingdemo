package com.example.filehandlingdemo.user.persistence.repositories;

import com.example.filehandlingdemo.user.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findUserByUsernameAndDeletedIsFalse(String username);
    Optional<User> findUserByIdAndDeletedIsFalse(Long id);

    boolean existsByUsername(String username);
    boolean existsByUsernameAndDeletedIsFalse(String username);

    Set<User> findAllByDeletedIsFalse();

//    Optional<User> findByDriver_DriverId(Long id);
    Optional<User> findByRef(String UUID);


}
