package com.example.filehandlingdemo.cars.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "car_model")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private long id;

    private String refNo = UUID.randomUUID().toString();

    @Column(name = "model_name" ,columnDefinition = "TEXT",unique = true)
    private String modelName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "make_id", nullable = false)
    @JsonIgnore
    private CarMake carMake;

}
