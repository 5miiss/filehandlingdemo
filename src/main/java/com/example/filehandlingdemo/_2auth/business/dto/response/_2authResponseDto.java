package com.example.filehandlingdemo._2auth.business.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@ToString
@Setter
@Getter
@AllArgsConstructor
public class _2authResponseDto {

        @JsonIgnore
        private Long id;

        @NotBlank(message = "Invalid Phone number: Empty number")
        @NotNull(message = "Invalid Phone number: Number is NULL")
        @Pattern(regexp = "^\\d{11}$", message = "Invalid phone number")
        private String phone;





}
