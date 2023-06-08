package com.example.filehandlingdemo.cars.buisness.mapper;


import com.example.filehandlingdemo.cars.buisness.dto.request.CarMakeDto;
import com.example.filehandlingdemo.cars.persistence.models.CarMake;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring" )
@Component
public interface CarMakeMapper {
    CarMake dtoToEntity(CarMakeDto carMakeDto);
    List<CarMake> dtoToEntity(List<CarMakeDto> carMakeDto);

    CarMakeDto entityToDto(CarMake carMake);
    List<CarMakeDto> entityToDto(List<CarMake> carMakes);


}
