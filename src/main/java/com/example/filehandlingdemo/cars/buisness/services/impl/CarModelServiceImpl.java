package com.example.filehandlingdemo.cars.buisness.services.impl;

import com.example.filehandlingdemo.cars.buisness.dto.request.CarModelRequestDto;
import com.example.filehandlingdemo.cars.buisness.dto.response.CarModelResponseDto;
import com.example.filehandlingdemo.cars.buisness.mapper.CarModelMapper;
import com.example.filehandlingdemo.cars.buisness.services.interfaces.CarMakeService;
import com.example.filehandlingdemo.cars.buisness.services.interfaces.CarModelService;
import com.example.filehandlingdemo.cars.persistence.models.CarModel;
import com.example.filehandlingdemo.cars.persistence.repository.CarModelRepo;
import com.example.filehandlingdemo.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepo carModelRepo;
    private final CarMakeService carMakeService;
    private final CarModelMapper carModelMapper;
    @Override
    public CarModelResponseDto findByName(String name) {
        return CarModelResponseDto.builder()
                        .message("the name for car model is ")
                                .code(200)
                                        .status(true)
                                                .carModelName( carModelMapper.entityToDto(carModelRepo.findByModelNameContainsIgnoreCase(name)
                                                        .orElseThrow(()->new ObjectNotFoundException("no car model found with that name !")))).build();

    }

    @Override
    public CarModelResponseDto findByRefNo(String refNo) {
        return CarModelResponseDto.builder()
                .message("the name for car model is ")
                .code(200)
                .status(true)
                .carModelName( carModelMapper.entityToDto(carModelRepo.findByRefNo(refNo)
                        .orElseThrow(()->new ObjectNotFoundException("no car model found with that name !")))).build();
    }

    @Override
    public CarModel findCArModelByRefNo(String refNo) {
        return carModelRepo.findByRefNo(refNo).orElseThrow(()->new ObjectNotFoundException("no model with that refNo"));
    }

    @Override
    public CarModelResponseDto findAllModels() {
        return CarModelResponseDto.builder()
                .message("all available models ")
                .code(200)
                .status(true)
                        .carModelName(carModelMapper.entityToDto(  carModelRepo.findAll())).build();
    }

    @Override
    public CarModelResponseDto findAllModelsByMake(String refNo) {
        return CarModelResponseDto.builder()
                .message("all available models for given make ")
                .code(200)
                .status(true)
                .carModelName(carModelMapper.entityToDto( carModelRepo.findByCarMake_RefNo(refNo))).build();
    }

    @Override
    public CarModelResponseDto insertByName(CarModelRequestDto carModelDto) {
        CarModel carModel =carModelRepo.findByModelNameContainsIgnoreCase(carModelDto.getModelName()).orElse(null);
        if (carModel == null){
            carModel = new CarModel();
            //find by name throws exception f name not contained in the database
            carModel.setCarMake(carMakeService.findByRefNo(carModelDto.getMakeRefNo()));
            carModel.setModelName(carModelDto.getModelName());

            return CarModelResponseDto.builder()
                    .message("successfully added model")
                    .code(200)
                    .status(true)
                    .carModelName(carModelMapper.entityToDto( carModelRepo.save(carModel))).build();
        }
        else{
            return CarModelResponseDto.builder()
                    .message("Model already present")
                    .code(409)
                    .status(false)
                    .build();
            }
    }

    @Override
    public CarModelResponseDto updateByName(String refNo, CarModelRequestDto carModelDto) {
        CarModel carModel =  carModelRepo.findByRefNo(refNo).orElseThrow(()->new ObjectNotFoundException("no car model found with that name !"));
        carModel.setCarMake(carMakeService.findByRefNo(carModelDto.getMakeRefNo()));
        carModel.setModelName(carModelDto.getModelName());

        return CarModelResponseDto.builder()
                .message("updated to database")
                .code(200)
                .status(true)
                .carModelName(carModelMapper.entityToDto( carModelRepo.save(carModel))).build();
    }

    @Override
    public void deleteByName(String refNo) {
        CarModel carModel =carModelRepo.findByRefNo(refNo).orElseThrow(()->new ObjectNotFoundException("no car model found with that name !"));
        carModelRepo.delete(carModel);
    }
}
