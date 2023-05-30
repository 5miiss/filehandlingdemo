package com.example.filehandlingdemo.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ValidatorChecksTest {
    @Autowired
    private ValidatorChecks validatorChecks;
    @Test
    void testGetCustomErrors() {

    }

    @Test
    void testLoadErrors() {
        assertEquals(28, validatorChecks.loadErrors());
    }

    @Test
    void testWriteToFile() {
        assertEquals(true, validatorChecks.writeToFile());
    }
}
