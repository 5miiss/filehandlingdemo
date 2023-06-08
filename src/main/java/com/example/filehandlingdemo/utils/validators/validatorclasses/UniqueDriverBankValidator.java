package com.example.filehandlingdemo.utils.validators.validatorclasses;


import com.example.filehandlingdemo.driver.persistence.repositories.DriverBankRepo;
import com.example.filehandlingdemo.utils.validators.validatoranootations.DriverBankValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
@AllArgsConstructor
public class UniqueDriverBankValidator implements ConstraintValidator<DriverBankValidator, String> {

    private final DriverBankRepo driverBankRepo;
    @Override
    public void initialize(DriverBankValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !driverBankRepo.existsByIBANAndDeletedFalse(s) ;
    }
}
