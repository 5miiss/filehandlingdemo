package com.example.filehandlingdemo.apikey.business.mapper;

import com.example.filehandlingdemo.apikey.business.dto.ApiKeyDto;
import com.example.filehandlingdemo.apikey.persistence.Models.ApiKey;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")

public interface ApiKeyMapper {

    ApiKey fromDtoToEntity(ApiKeyDto apiKeyDto);
    List<ApiKey> fromDtoToEntity(List<ApiKeyDto> apiKeyDto);

    ApiKeyDto fromEntityToDto(ApiKey apiKey);
    List<ApiKeyDto> fromEntityToDto(List<ApiKey> apiKey);



}
