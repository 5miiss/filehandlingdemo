package com.example.filehandlingdemo.apikey.business.service.interfaces;

import com.example.filehandlingdemo.apikey.business.dto.ApiKeyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiKeyService {
    ApiKeyDto getApiKey(String refNo);
    List<ApiKeyDto> getAPiKeys();
    ApiKeyDto generateApiKey(String name);
    ApiKeyDto updateApiKey( ApiKeyDto apiKeyDto);
    boolean deleteAPiKey(String refNo);

}
