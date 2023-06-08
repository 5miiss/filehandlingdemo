package com.example.filehandlingdemo.exceptions;

import com.example.filehandlingdemo.driver.business.dto.Response.DriverResponseDto;
import com.example.filehandlingdemo.utils.validators.ValidatorChecks;
import lombok.AllArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private final ValidatorChecks validatorChecks;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DriverResponseDto handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new DriverResponseDto("validation errors in custom validators", false, 2000,validatorChecks.getCustomErrors(errors));
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    public final DriverResponseDto ObjectNotFoundError(ObjectNotFoundException ex){
        String error = ex.getMessage();
        return new DriverResponseDto(error,false, 200,error);
    }

    @ExceptionHandler(Exception.class)
    public final DriverResponseDto handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return  new DriverResponseDto(errors.get(0),false, 200,errors);    }

    @ExceptionHandler(RuntimeException.class)
    public final DriverResponseDto handleRuntimeExceptions(RuntimeException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new DriverResponseDto(errors.get(0),false, 200,errors);
    }


}
