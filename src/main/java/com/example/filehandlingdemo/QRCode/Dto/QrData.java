package com.example.filehandlingdemo.QRCode.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QrData {
    String sellerName;
    @NotNull
    @Min(value = 100000000000000L, message = "Value must be exactly 15 digits")
    @Max(value = 999999999999999L, message = "Value must be exactly 15 digits")
    Long vatNum ;
    LocalDateTime time ;
    Double totalPrice ;
    Double vat;
}
