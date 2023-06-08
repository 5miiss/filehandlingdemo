package com.example.filehandlingdemo.utils.validators.validatoranootations;

import com.example.filehandlingdemo.utils.validators.validatorclasses.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface DriverEmailValidator {
    String message() default "email must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
