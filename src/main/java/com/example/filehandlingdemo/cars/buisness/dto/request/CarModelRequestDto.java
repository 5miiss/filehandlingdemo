package com.example.filehandlingdemo.cars.buisness.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CarModelRequestDto {
    private String makeRefNo;
    private String modelName;
}
