package com.example.filehandlingdemo.cars.buisness.services.impl;

import com.example.filehandlingdemo.cars.buisness.dto.request.CarMakeDto;
import com.example.filehandlingdemo.cars.buisness.mapper.CarMakeMapper;
import com.example.filehandlingdemo.cars.buisness.services.interfaces.CarMakeService;
import com.example.filehandlingdemo.cars.persistence.models.CarMake;
import com.example.filehandlingdemo.cars.persistence.repository.CarMakeRepo;
import com.example.filehandlingdemo.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CarMakeServiceImpl implements CarMakeService {

    private final CarMakeRepo carMakeRepo;
    private final CarMakeMapper carMakeMapper;

    @Override
    public CarMake findByName(String name) {
        return carMakeRepo.findByMakeNameContainsIgnoreCase(name)
                .orElseThrow(()->new ObjectNotFoundException("no car make with that name you have to insert first"));

    }

    @Override
    public CarMake findByRefNo(String name) {
        return carMakeRepo.findByRefNo(name)
                .orElseThrow(()->new ObjectNotFoundException("no car make with that refNo you have to insert first"));
    }


    @Override
    public List<CarMakeDto> findAllMakes() {

        return carMakeMapper.entityToDto( carMakeRepo.findAll());
    }

    @Override
    public String findById(int id) {
        return carMakeRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException("no car found with that id")).getMakeName() ;
    }

    @Override
    public CarMakeDto insertByName(CarMakeDto carModelDto) {
        CarMake carMake =  carMakeRepo.findByMakeNameContainsIgnoreCase(carModelDto.getMakeName()).orElse(null);
        if (carMake == null) {
            carMake = new CarMake();
            carMake.setMakeName(carModelDto.getMakeName());
            return carMakeMapper.entityToDto( carMakeRepo.save(carMake));
        }
        else
            throw  new ObjectNotFoundException("car With same Make already present !");
    }

    @Override
    public CarMakeDto updateByName(String refNo, CarMakeDto carModelDto) {
        CarMake carMake =  carMakeRepo.findByRefNo(refNo).orElseThrow(()-> new ObjectNotFoundException("no car make with that name to update"));
        carMake.setMakeName(carModelDto.getMakeName());
        return  carMakeMapper.entityToDto( carMakeRepo.save(carMake));
 }

    @Override
    public void deleteByName(String name) {
        CarMake carMake =  carMakeRepo.findByRefNo(name).orElseThrow(()-> new ObjectNotFoundException("no car make with that name to update"));
        carMakeRepo.delete(carMake);
    }
}
