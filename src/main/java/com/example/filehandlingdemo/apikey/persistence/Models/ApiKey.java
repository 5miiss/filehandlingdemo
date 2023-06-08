package com.example.filehandlingdemo.apikey.persistence.Models;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String apiKey = UUID.randomUUID().toString();
    private String refNo = UUID.randomUUID().toString();
    private String name;
    private boolean isExpired = false;

    public ApiKey(String name) {
        this.name = name;
    }
}
