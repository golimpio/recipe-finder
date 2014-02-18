package com.example.services;

import static org.fest.assertions.api.Assertions.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class FridgeServiceTest {

    private FridgeService service;

    @BeforeMethod
    public void init()
    {
        service = new FridgeService();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addItems_shouldThrowAnException_WhenCsvIsInvalid() throws IOException {
        String invalidCsv = "bread,10,slices,25/12/2014\n" +
                            "cheese,10,slices,25/12/2014, unexpected-column";

        service.addItems(invalidCsv);
    }
}
