package com.example.filehandlingdemo.cars.persistence.repository;

import com.example.filehandlingdemo.cars.persistence.models.CarMake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarMakeRepo extends JpaRepository<CarMake, Integer> {


    Optional<CarMake> findByMakeNameContainsIgnoreCase(String carMakeName);


    Optional<CarMake> findByRefNo(String refNo);

}
