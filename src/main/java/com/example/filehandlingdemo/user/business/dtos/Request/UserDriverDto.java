package com.example.filehandlingdemo.user.business.dtos.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDriverDto {
    private String phone;
    private String email;
    private String name;
    private String nationality;
    private String idNumber;
    private String folderPath;
    private String licenceNumber;
    private Double deliveryRange;
    private String city;

    private Set<String> orderPoint;
    private Set<String> deliveryPoint;
}
