package com.example.filehandlingdemo.cars.buisness.services.interfaces;

import com.example.filehandlingdemo.cars.buisness.dto.request.CarMakeDto;
import com.example.filehandlingdemo.cars.persistence.models.CarMake;

import java.util.List;

public interface CarMakeService {

    CarMake findByName(String name);
    CarMake findByRefNo(String name);

    List<CarMakeDto> findAllMakes();
    String findById(int id);

    CarMakeDto insertByName(CarMakeDto carModelDto);
    CarMakeDto updateByName(String name, CarMakeDto carModelDto);
    void deleteByName(String name);
}
