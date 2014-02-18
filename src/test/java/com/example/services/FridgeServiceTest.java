package com.example.services;

import com.example.repository.FridgeRepository;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class FridgeServiceTest {

    private FridgeService service;

    @BeforeMethod
    public void init()
    {
        service = new FridgeService();
        FridgeRepository.instance().removeAll();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addItems_shouldThrowAnException_WhenCsvIsInvalid() throws IOException {
        String invalidCsv = "bread,10,slices,25/12/2014\n" +
                            "cheese,10,slices,25/12/2014, unexpected-column";

        service.addItems(invalidCsv);
        fail("Adding items should have failed!");
    }

    @Test
    public void addItems_shouldPopulateTheRepository() throws IOException {
        String csv = "bread,10,slices,25/12/2014\n" +
                     "cheese,10,slices,25/12/2014";

        FridgeRepository.instance().removeAll();

        service.addItems(csv);
        assertThat(FridgeRepository.instance().get()).hasSize(2);
    }
}
