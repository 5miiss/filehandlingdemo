package com.example.filehandlingdemo._2auth.business.service;

import com.example.filehandlingdemo._2auth.business.dto._2authResponseDto;
import com.example.filehandlingdemo._2auth.business.dto.request.ValidAuthDto;
import com.example.filehandlingdemo._2auth.business.dto.request._2authDto;


public interface _2authServices {


    _2authResponseDto add_2auth(_2authDto authDto);

    _2authResponseDto get_2authById(Long id);

    _2authResponseDto get_2authByPhone(String phone);

    void remove_2authById(Long id);

    void remove_2authByPhone(String phone);
    _2authResponseDto codeVerification(ValidAuthDto authDto);



}
