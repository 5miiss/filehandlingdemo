package com.example.filehandlingdemo.utils.validators.validatorclasses;

import com.example.filehandlingdemo.driver.persistence.repositories.CarRepo;
import com.example.filehandlingdemo.utils.validators.validatoranootations.CarPlateNumberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
public class UniqueCarPlateNumberValidator implements ConstraintValidator<CarPlateNumberValidator,String> {
    private final CarRepo carRepo;
    @Override
    public void initialize(CarPlateNumberValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !carRepo.existsByCarPlateNoAndDeletedFalse(s);
    }
}
