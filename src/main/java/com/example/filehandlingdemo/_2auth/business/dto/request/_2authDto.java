package com.example.filehandlingdemo._2auth.business.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;


@ToString
@Setter
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class _2authDto {

        private Long id;

        @NotNull(message = "Invalid Phone number: Number is NULL")
//        @Pattern(regexp = "^\\d{11}$", message = "Invalid phone number")
        private String phone;

        private Type type;





}
