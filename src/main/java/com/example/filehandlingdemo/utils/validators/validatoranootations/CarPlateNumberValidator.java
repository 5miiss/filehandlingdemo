package com.example.filehandlingdemo.utils.validators.validatoranootations;


import com.example.filehandlingdemo.utils.validators.validatorclasses.UniqueCarPlateNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCarPlateNumberValidator.class)
public @interface CarPlateNumberValidator {
    String message() default "Plate Number must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
