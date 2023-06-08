package com.example.filehandlingdemo.cars.buisness.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CarModelDto {

    private String makeName;
    private String modelName;
    private String refNo;

}
