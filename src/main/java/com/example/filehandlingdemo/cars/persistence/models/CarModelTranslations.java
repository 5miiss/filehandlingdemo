package com.example.filehandlingdemo.cars.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarModelTranslations {
    private String en;
    private String ar;
    private String ur;

}
