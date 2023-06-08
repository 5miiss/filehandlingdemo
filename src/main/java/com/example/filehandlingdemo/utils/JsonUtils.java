package com.example.filehandlingdemo.utils;


import com.example.filehandlingdemo.cars.persistence.models.CarMake;
import com.example.filehandlingdemo.cars.persistence.models.CarModelTranslations;
import com.example.filehandlingdemo.cars.persistence.repository.CarMakeRepo;
import com.example.filehandlingdemo.cars.persistence.repository.CarModelRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class JsonUtils {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final CarModelRepo carModelRepo;

    private final CarMakeRepo carMakeRepo;



    public List<CarModelTranslations> checkFile() throws IOException {

//        ClassPathResource staticDataResource = new ClassPathResource("cars.json");
//        String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);
//        JsonNode jsonArray = objectMapper.readValue(staticDataString, JsonNode.class);
//
//        String jsonArrayAsString = objectMapper.writeValueAsString(jsonArray);
//
//        List<CarModelTranslations> translations = objectMapper.readValue(jsonArrayAsString, new TypeReference<>() {
//        });
//        System.out.println(translations.isEmpty());
//
//        System.out.println(translations.size());
//
//        Iterator<CarModelTranslations> carModelTranslationsIterator = translations.iterator();
//        List<CarModel> all = carModelRepo.findAll();
//        Iterator<CarModel> carModelIterator = all.iterator();
//        while (carModelIterator.hasNext() && carModelTranslationsIterator.hasNext()){
//            CarModel next = carModelIterator.next();
//            CarModelTranslations nextTranslation = carModelTranslationsIterator.next();
//            next.setModelName(new Gson().toJson(nextTranslation));
//            carModelRepo.save(next);
//        }
//        return  translations;


        ClassPathResource staticDataResource = new ClassPathResource("carMake.json");
        String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);
        JsonNode jsonArray = objectMapper.readValue(staticDataString, JsonNode.class);

        String jsonArrayAsString = objectMapper.writeValueAsString(jsonArray);

        List<CarModelTranslations> translations = objectMapper.readValue(jsonArrayAsString, new TypeReference<>() {
        });
        System.out.println(translations.isEmpty());

        System.out.println(translations.size());

        Iterator<CarModelTranslations> carModelTranslationsIterator = translations.iterator();
        List<CarMake> all = carMakeRepo.findAll();
        Iterator<CarMake> carMakeIterator = all.iterator();
        while (carMakeIterator.hasNext() && carModelTranslationsIterator.hasNext()){
            CarMake next = carMakeIterator.next();
            CarModelTranslations nextTranslation = carModelTranslationsIterator.next();
            next.setMakeName(new Gson().toJson(nextTranslation));
            carMakeRepo.save(next);
        }
        return  translations;

    }
}
