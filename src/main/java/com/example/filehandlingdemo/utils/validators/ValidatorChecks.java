package com.example.filehandlingdemo.utils.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ValidatorChecks {
    private Map<String,ValidatorErrors> errors = new HashMap<>(40);

    public List<ValidatorErrors> getCustomErrors(List<String> erorrsList){
        loadErrors();
        return erorrsList.stream().map(err-> errors.get(err)).collect(Collectors.toList());
    }
    public void loadErrors(){
     ClassPathResource resource = new ClassPathResource("errors.txt");

        File file = null;
        try {
            file = resource.getFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String errorString = scanner.nextLine();
                String[] parts = errorString.split(":");
                int errorCode = Integer.parseInt(parts[0]);
                String errorMessage = parts[1];
                errors.put(errorMessage, new ValidatorErrors(errorCode, errorMessage));
            }
         } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
    public void writeToFile(){
        try {
//         FileWriter fileWriter = new FileWriter(file);
//         PrintWriter printWriter = new PrintWriter(fileWriter);
//         FileOutputStream fos = new FileOutputStream("errors.txt");
//         ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//         for (int i = 0; i < list.size(); i++) {
//             log.info("{}",list.get(i));
//
//             oos.writeObject(list.get(i));
//
//         }
//         oos.close();
            List<ValidatorErrors> list = new ArrayList<>(errors.values());
            PrintWriter pw = new PrintWriter(new FileOutputStream("src/main/resources/errors.txt"));
            for (ValidatorErrors validatorErrors : list)
                pw.println(validatorErrors.getErrorCode() + ":" +validatorErrors.getErrorMessage());
            pw.close();
        }catch (IOException e){
            log.error(e.getMessage());
        }
    }
}
