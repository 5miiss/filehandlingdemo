package com.example.filehandlingdemo.user.business.dtos.Request;

import com.example.filehandlingdemo.utils.validators.validatoranootations.PhoneValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRegisterDto {
    @PhoneValidator
    private String phone;
    private String oneSignalId="N/A";
    private String lang="ar";


}
