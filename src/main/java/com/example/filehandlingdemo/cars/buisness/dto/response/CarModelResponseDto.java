package com.example.filehandlingdemo.cars.buisness.dto.response;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class CarModelResponseDto {
    String message;
    boolean status;
    int code;
    private Object carModelName;

}
