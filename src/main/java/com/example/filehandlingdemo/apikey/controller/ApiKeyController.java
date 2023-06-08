package com.example.filehandlingdemo.apikey.controller;

import com.example.filehandlingdemo.apikey.business.dto.ApiKeyDto;
import com.example.filehandlingdemo.apikey.business.dto.ApiKeyResponseDto;
import com.example.filehandlingdemo.apikey.business.service.interfaces.ApiKeyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/v1")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@AllArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;
    @GetMapping("/apikey")
    public ApiKeyResponseDto getAllApiKeys()
    {
        return ApiKeyResponseDto.builder()
                .message("All available apikeys !")
                .status(true)
                .code(200)
                .ApiKeys(apiKeyService.getAPiKeys())
                .build();
    }

    @GetMapping("/apikey/{refNo}")
    public ApiKeyResponseDto getApiKey(@PathVariable String refNo)
    {
        return ApiKeyResponseDto.builder()
                .message("the apikey !")
                .status(true)
                .code(200)
                .ApiKeys(Collections.singletonList(apiKeyService.getApiKey(refNo)))
                .build();
    }
    @PostMapping("/apikey")
    public ApiKeyResponseDto generateApiKey(@RequestParam String name )
    {

        return new ApiKeyResponseDto("generated apikey !",true, 200, apiKeyService.generateApiKey(name));

    }
    @PutMapping("/apikey")
    public ApiKeyResponseDto updateApiKey(@RequestBody ApiKeyDto apiKeyDto)
    {
        return ApiKeyResponseDto.builder()
                .message("update apikey is done !")
                .status(true)
                .code(200)
                .ApiKeys(Collections.singletonList(apiKeyService.updateApiKey(apiKeyDto)))
                .build();
    }
    @DeleteMapping("/apikey")
    public ApiKeyResponseDto deleteApiKey(@RequestParam String refNo)
    {
        apiKeyService.deleteAPiKey(refNo);
        return ApiKeyResponseDto.builder()
                .message("delete the apikey is done !")
                .status(true)
                .code(200)
                .ApiKeys(new ArrayList<>())
                .build();
    }
}
