package com.example.filehandlingdemo.utils.validators.validatorclasses;

import com.example.filehandlingdemo.driver.persistence.repositories.DriverRepo;
import com.example.filehandlingdemo.utils.validators.validatoranootations.PhoneValidator;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniquePhoneValidator implements ConstraintValidator<PhoneValidator, String> {
    private final DriverRepo driverRepo;
    @Override
    public void initialize(PhoneValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phone != null && !driverRepo.existsByPhone(phone);

    }
}
