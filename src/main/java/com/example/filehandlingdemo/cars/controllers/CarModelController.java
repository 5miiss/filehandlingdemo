package com.example.filehandlingdemo.cars.controllers;

import com.example.filehandlingdemo.cars.buisness.dto.request.CarModelRequestDto;
import com.example.filehandlingdemo.cars.buisness.dto.response.CarModelResponseDto;
import com.example.filehandlingdemo.cars.buisness.services.interfaces.CarModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carModel")
public class CarModelController {
    private final CarModelService carModelService;

    @GetMapping()
    public CarModelResponseDto getAll(){
        return carModelService.findAllModels();
    }

    @GetMapping("/make")
    public CarModelResponseDto getAllByMake(@RequestParam String refNo){
        return carModelService.findAllModelsByMake(refNo);
    }

    @GetMapping("/ref/{refNo}")
    public ResponseEntity<?> getByRef(@PathVariable String refNo){
        return ResponseEntity.ok( carModelService.findByRefNo(refNo));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name){
        return ResponseEntity.ok( carModelService.findByName(name));
    }

    @PostMapping()
    public ResponseEntity<?> InsertCarModel(@RequestBody CarModelRequestDto carModelRequestDto){
        return ResponseEntity.ok().body( carModelService.insertByName(carModelRequestDto));
    }

    @PutMapping()
    public ResponseEntity<?> updateCarModel(@RequestParam String refNo,@RequestBody CarModelRequestDto carModelRequestDto){
        return ResponseEntity.ok().body( carModelService.updateByName(refNo,carModelRequestDto));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCarModel(@RequestParam String refNo){
        carModelService.deleteByName(refNo);
        return ResponseEntity.noContent().build();
    }
}
