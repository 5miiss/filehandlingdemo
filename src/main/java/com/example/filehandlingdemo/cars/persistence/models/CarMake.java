package com.example.filehandlingdemo.cars.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "car_make")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CarMake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "make_id")
    private int id;
    @Column(name = "Make",columnDefinition = "TEXT",unique = true)
    private String makeName;
    private String refNo = UUID.randomUUID().toString();
    @OneToMany(mappedBy = "carMake",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CarModel> carModels;

    @Override
    public String toString() {
        return "CarMake{" +
                "id=" + id +
                ", carMakeName='" + makeName + '\'' +
                ", refNo='" + refNo + '\'' +
                '}';
    }
}
