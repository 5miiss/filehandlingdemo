package com.example.filehandlingdemo._2auth.persistence.repositories;

import com.example.filehandlingdemo._2auth.persistence.entities._2auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface _2authRepo extends JpaRepository<_2auth, Long> {

    Optional<_2auth> findByPhone(String phone);
    _2auth findByPhoneAndOtp(String phone, String otp);
    Boolean existsByPhone(String phone);
}
