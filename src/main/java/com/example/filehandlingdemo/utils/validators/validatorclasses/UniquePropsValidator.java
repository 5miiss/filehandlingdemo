package com.example.filehandlingdemo.utils.validators.validatorclasses;


import com.example.filehandlingdemo.driver.persistence.repositories.DriverRepo;
import com.example.filehandlingdemo.utils.validators.validatoranootations.UniqueDriverPropsValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UniquePropsValidator  implements ConstraintValidator<UniqueDriverPropsValidator, String> {
    private final DriverRepo driverRepo;
    @Override
    public void initialize(UniqueDriverPropsValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !driverRepo.existsByNameOrIdNumberOrLicenceNumber(s,s,s);
    }
}
