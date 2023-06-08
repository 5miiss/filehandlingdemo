package com.example.filehandlingdemo.cars.buisness.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CarMakeDto {
    private String makeName;
    private String refNo;

    public CarMakeDto(String carMakeName) {
        this.makeName = carMakeName;
    }
}
