package com.example.filehandlingdemo.driver.business.dto.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

    @Data
    @NoArgsConstructor
    @Component
    public class DriverResponseDto {
        String message;
        boolean status;
        Integer code;
        Object data;
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime date = LocalDateTime.now();

        public DriverResponseDto(String message, boolean status, Integer code, Object data) {
            this.message = message;
            this.status = status;
            this.code = code;
            this.data = data;
        }


    }

