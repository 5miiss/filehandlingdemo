package com.example.filehandlingdemo._2auth.business.mapper;

import com.example.filehandlingdemo._2auth.business.dto.request._2authDto;
import com.example.filehandlingdemo._2auth.persistence.entities._2auth;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;


@Mapper(componentModel = "spring" )
@Component
public interface _2authMapper {

    _2authDto entityToDto(_2auth auth);

    _2auth dtoToEntity(_2authDto authDto);

    Set<_2authDto> listEntitiesToDto(Set<_2auth> auths);
    Set<_2auth> listDtoToEntites(Set<_2authDto> authDtos);

}
