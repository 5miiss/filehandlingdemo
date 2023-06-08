package com.example.filehandlingdemo.cars.buisness.services.interfaces;

import com.example.filehandlingdemo.cars.buisness.dto.request.CarModelRequestDto;
import com.example.filehandlingdemo.cars.buisness.dto.response.CarModelResponseDto;
import com.example.filehandlingdemo.cars.persistence.models.CarModel;

public interface CarModelService {

    CarModelResponseDto findByName(String name);
    CarModelResponseDto findByRefNo(String name);
    CarModel findCArModelByRefNo(String name);


    CarModelResponseDto findAllModels();
    CarModelResponseDto findAllModelsByMake(String carMakeName);

    CarModelResponseDto insertByName(CarModelRequestDto carModelDto);
    CarModelResponseDto updateByName(String name, CarModelRequestDto carModelDto);
    void deleteByName(String name);

}
