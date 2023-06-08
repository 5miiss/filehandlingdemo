package com.example.filehandlingdemo.cars.persistence.repository;

import com.example.filehandlingdemo.cars.persistence.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarModelRepo extends JpaRepository<CarModel, Long> {
    List<CarModel> findByCarMake_RefNo(String refNo);

    @Query("select c from CarModel c where upper(c.modelName) like upper(concat('%', ?1, '%'))")
    Optional<CarModel> findByModelNameContainsIgnoreCase(String modelName);


    @Query("select c from CarModel c where c.refNo = ?1")
    Optional<CarModel> findByRefNo(String refNo);
}
