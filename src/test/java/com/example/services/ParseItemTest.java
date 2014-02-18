package com.example.services;

import com.example.models.FridgeItem;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ParseItemTest {

    @DataProvider
    private static Object[][] itemsWithInvalidNumberOfColumns() {
        return new Object[][] {
                new Object[] {"bread,10,slices"},
                new Object[] {"bread,10,slices,25/12/2014,25/12/2014"},
        };
    }

    @DataProvider
    private static Object[][] itemsWithInvalidField() {
        return new Object[][] {
                new Object[] {new String[] {"bread", "10", "slices", "/12/2014"}},
                new Object[] {new String[] {"cheese", "xx", "slices", "25/12/2014"}},
                new Object[] {new String[] {"butter", "250", "grams", "32/12/2014"}},
                new Object[] {new String[] {"mixed salad", "150", "grammar", "26/12/2013"}}
        };
    }

    @DataProvider
    private static Object[][] validItems() {
        return new Object[][] {
                new Object[] {new String[] {"bread", "   10", "  slices", "1/12/2014"}},
                new Object[] {new String[] {"banana", "3", "of  ", "15/5/2014"}},
                new Object[] {new String[] {"watermelon", "3", "OF", "15/12/2014"}},
                new Object[] {new String[] {"butter", "250", "GRAMS", " 02/03/2014"}},
                new Object[] {new String[] {"peanut butter",  "250", "grams ", "2/3/2014"}},
                new Object[] {new String[] {"vanilla extract", "20", " ml", "26/12/2013"}}
        };
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void parseFridgeItem_ShouldThrowException_WhenItemArgumentIsNull() {
        ItemParser.parseFridgeItem(null);
    }

    @Test(dataProvider = "itemsWithInvalidNumberOfColumns", expectedExceptions = IllegalArgumentException.class)
    public void parseFridgeItem_ShouldThrowException_WhenNumberOfColumnsDoNotMatch(String[] item) {
        ItemParser.parseFridgeItem(item);
    }

    @Test(dataProvider = "itemsWithInvalidField", expectedExceptions = IllegalArgumentException.class)
    public void parseFridgeItem_ShouldThrowException_WhenItemIsNotValid(String[] item) {
        ItemParser.parseFridgeItem(item);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Test(dataProvider = "validItems")
    public void parseFridgeItem(String[] item) {
        FridgeItem fridgeItem = ItemParser.parseFridgeItem(item);

        assertThat(fridgeItem.getItem()).isNotEmpty();
        assertThat(fridgeItem.getAmount()).isGreaterThan(0);
        assertThat(fridgeItem.getUnit()).isNotNull();
        assertThat(fridgeItem.getUseBy()).isNotNull();
    }
}
