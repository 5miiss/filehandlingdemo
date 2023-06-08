package com.example.filehandlingdemo.cars.controllers;

import com.example.filehandlingdemo.cars.buisness.dto.request.CarMakeDto;
import com.example.filehandlingdemo.cars.buisness.mapper.CarMakeMapper;
import com.example.filehandlingdemo.cars.buisness.services.interfaces.CarMakeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carMake")
public class CarMakeController {
    private final CarMakeService carMakeService;
    private final CarMakeMapper carMakeMapper;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return
                ResponseEntity.ok(carMakeService.findAllMakes());
    }

    @GetMapping("/{refNo}")
    public ResponseEntity<?> getByRef(@PathVariable String refNo){
        return ResponseEntity.ok( carMakeMapper.entityToDto( carMakeService.findByRefNo(refNo)));
    }

    @PostMapping()
    public ResponseEntity<?> InsertCarMake(@RequestBody CarMakeDto carMakeDto){
        return ResponseEntity.ok().body( carMakeService.insertByName(carMakeDto));
    }

    @PutMapping()
    public ResponseEntity<?> updateCarMake(@RequestParam String refNo,@RequestBody CarMakeDto carMakeDto){
        return ResponseEntity.ok().body( carMakeService.updateByName(refNo,carMakeDto));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCarMake(@RequestParam String refNo){
        carMakeService.deleteByName(refNo);
        return ResponseEntity.noContent().build();
    }

}
