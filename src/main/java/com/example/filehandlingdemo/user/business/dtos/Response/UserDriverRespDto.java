package com.example.filehandlingdemo.user.business.dtos.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDriverRespDto {
    private String email;
    private String city;
    private String licenceNumber;
    private String idNumber;
    private Set<String> orderPoint;
    private Set<String> deliveryPoint;
    private String phone;
    private String name;
    private String nationality;
    private String folderPath;
    private Double deliveryRange;
    private Long driverId;

}
