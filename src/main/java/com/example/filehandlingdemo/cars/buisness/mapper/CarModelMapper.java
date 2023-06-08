package com.example.filehandlingdemo.cars.buisness.mapper;


import com.example.filehandlingdemo.cars.buisness.dto.request.CarModelDto;
import com.example.filehandlingdemo.cars.buisness.services.interfaces.CarMakeService;
import com.example.filehandlingdemo.cars.persistence.models.CarModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring" )
@Component
public abstract class CarModelMapper {
    @Autowired
    CarMakeService carMakeService;
    public abstract CarModel dtoToEntity(CarModelDto carModelDto);
    public abstract Set<CarModel> dtoToEntity(Set<CarModelDto> carModelDtos);

    @Mapping(target = "makeName", expression = "java(carMakeService.findById(carModel.getCarMake().getId()))")
    public abstract CarModelDto entityToDto(CarModel carModel);
    public abstract List<CarModelDto> entityToDto(List<CarModel> carModels);
}
