package com.example.filehandlingdemo.apikey.business.service.impl;

import com.example.filehandlingdemo.apikey.business.Exception.ApiKeyException;
import com.example.filehandlingdemo.apikey.business.dto.ApiKeyDto;
import com.example.filehandlingdemo.apikey.business.mapper.ApiKeyMapper;
import com.example.filehandlingdemo.apikey.business.service.interfaces.ApiKeyService;
import com.example.filehandlingdemo.apikey.persistence.Models.ApiKey;
import com.example.filehandlingdemo.apikey.persistence.Repository.ApiKeyRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {
    private final ApiKeyRepo apiKeyRepo;
    private final ApiKeyMapper apiKeyMapper;
    @Override
    public ApiKeyDto getApiKey(String refNo) {
        return apiKeyMapper.fromEntityToDto(apiKeyRepo.findByRefNo(refNo).orElseThrow(()->new ApiKeyException("no APiKey Found !")));
    }

    @Override
    public List<ApiKeyDto> getAPiKeys() {
        return apiKeyMapper.fromEntityToDto(apiKeyRepo.findAll());
    }

    @Override
    public ApiKeyDto generateApiKey(String name) {
        log.info("generate");
        ApiKey apiKey = new ApiKey(name);
        return apiKeyMapper.fromEntityToDto(apiKeyRepo.save( apiKey));
    }

    @Override
    public ApiKeyDto updateApiKey( ApiKeyDto apiKeyDto) {
        ApiKey apiKey =  apiKeyRepo.findByRefNo(apiKeyDto.getRefNo()).orElseThrow(()->new ApiKeyException("no APiKey Found !"));
        apiKey.setApiKey(apiKeyDto.getApiKey());
        apiKey.setName(apiKeyDto.getName());
        apiKey.setExpired(apiKeyDto.isExpired());
        return apiKeyMapper.fromEntityToDto( apiKeyRepo.saveAndFlush(apiKey));
    }

    @Override
    public boolean deleteAPiKey(String refNo) {
        ApiKey apiKey =  apiKeyRepo.findByRefNo(refNo).orElseThrow(()->new ApiKeyException("no APiKey Found !"));
        apiKeyRepo.delete(apiKey);
        return true;
    }
}
