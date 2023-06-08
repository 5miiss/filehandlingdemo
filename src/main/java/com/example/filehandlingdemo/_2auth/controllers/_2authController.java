package com.example.filehandlingdemo._2auth.controllers;


import com.example.filehandlingdemo._2auth.business.dto._2authResponseDto;
import com.example.filehandlingdemo._2auth.business.dto.request.ValidAuthDto;
import com.example.filehandlingdemo._2auth.business.dto.request._2authDto;
import com.example.filehandlingdemo._2auth.business.service._2authServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Auth")
public class _2authController {

    private final _2authServices authServices;

    @PostMapping("/sendOtp")
    public _2authResponseDto sendOtp(@RequestBody @Valid _2authDto authDto){
        return authServices.add_2auth(authDto);
    }
   @PostMapping("/resendOtp")
    public _2authResponseDto resendOtp(@RequestBody @Valid _2authDto authDto){
        return authServices.add_2auth(authDto);
    }
    @PostMapping("/Verification")
    public _2authResponseDto verification(@RequestBody ValidAuthDto authDto){return  authServices.codeVerification(authDto);}

    @GetMapping("test/{id}")
    public boolean test(@PathVariable int id){
        return true;
    }
}
