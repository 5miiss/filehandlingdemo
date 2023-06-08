package com.example.filehandlingdemo._2auth.business.serviceImp;

import com.example.filehandlingdemo._2auth.business.dto._2authResponseDto;
import com.example.filehandlingdemo._2auth.business.dto.request.Type;
import com.example.filehandlingdemo._2auth.business.dto.request.ValidAuthDto;
import com.example.filehandlingdemo._2auth.business.dto.request._2authDto;
import com.example.filehandlingdemo._2auth.business.mapper._2authMapper;
import com.example.filehandlingdemo._2auth.business.service._2authServices;
import com.example.filehandlingdemo._2auth.persistence.entities._2auth;
import com.example.filehandlingdemo._2auth.persistence.repositories._2authRepo;

import com.example.filehandlingdemo.exceptions.ObjectNotFoundException;
import com.example.filehandlingdemo.user.persistence.entities.User;
import com.example.filehandlingdemo.user.persistence.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class _2authServicesImp implements _2authServices {

    private final _2authRepo authRepo;
    private final _2authMapper authMapper;
    private final UserRepo userRepo;


    public String generateOTP(){
        return  RandomStringUtils.random(4,false,true);
    }

    @Override
    public _2authResponseDto add_2auth(_2authDto authDto) {
        if(authDto.getType().equals(Type.LOGIN)){
            User user =userRepo.findUserByUsernameAndDeletedIsFalse(authDto.getPhone()).orElse(null) ;

            if (user==null)
                return new _2authResponseDto("Phone Not Found",false,2002, null);

        }else {
            if(userRepo.existsByUsername(authDto.getPhone())){
                return new _2authResponseDto("Phone Already Exist",false,2001, null);
            }
        }
        if(authRepo.existsByPhone(authDto.getPhone())){
            remove_2authByPhone(authDto.getPhone());
        }
        authDto.setId(null);
        _2auth auth = authMapper.dtoToEntity(authDto);
        auth.setOtp(generateOTP());
        authRepo.save(auth);
        return  new _2authResponseDto("auth Saved Successfully",true,201, auth);
    }

    @Override
    public _2authResponseDto get_2authById(Long id) {
        _2auth auth = authRepo.findById(id).orElseThrow( () -> new ObjectNotFoundException("Auth Object Not Found"));

        _2authDto authDto = authMapper.entityToDto(auth);
        return new _2authResponseDto("Auth get Successfully", true, 200, authDto);
    }

    @Override
    public _2authResponseDto get_2authByPhone(String phone) {
        _2auth auth = authRepo.findByPhone(phone).orElseThrow( () -> new ObjectNotFoundException("Auth Object Not Found"));

        _2authDto authDto = authMapper.entityToDto(auth);
        return new _2authResponseDto("Auth get Successfylly", true, 200, authDto);
    }

    @Override
    public void remove_2authById(Long id) {
        _2auth auth = authRepo.findById(id).orElseThrow( () -> new ObjectNotFoundException("Auth Object Not Found"));
        authRepo.delete(auth);
    }

    @Override
    public void remove_2authByPhone(String phone) {
        _2auth auth = authRepo.findByPhone(phone).orElseThrow( () -> new ObjectNotFoundException("Auth Object Not Found"));
        authRepo.delete(auth);
    }


    @Override
    public _2authResponseDto codeVerification(ValidAuthDto authDto) {
        log.info("authDto {} ",authDto);

        _2auth auth = authRepo.findByPhoneAndOtp(authDto.getPhone(),authDto.getOtp());
        if(auth != null){
            if(DateIsExpired(auth.getCreatedAt())){
                authRepo.delete(auth);
                return new _2authResponseDto("expired",false,2005,null);

            }
            authRepo.delete(auth);
            return new _2authResponseDto("succeed",true,200,authDto);
        }
        return new _2authResponseDto("failed",false,2006,null);
    }


    public boolean DateIsExpired(Date createdAt) {
        Date currentDate = new Date();
        int diffInTime = (int)( (currentDate.getTime() - createdAt.getTime()) / (1000 * 60));
        return diffInTime > 2;
    }


}
