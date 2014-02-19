package com.example.services;

import com.example.models.FridgeItem;
import com.example.models.Unit;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.nullToEmpty;

final class ItemParser {

    private ItemParser() {}

    public static FridgeItem parseFridgeItem(String[] columns) {

        checkArgument(columns.length == 4, "Item should have exactly 4 fields: %s", Arrays.toString(columns));

        return new FridgeItem(nullToEmpty(columns[0]).trim(),
                                          getAmount(nullToEmpty(columns[1]).trim()),
                                          getUnit(nullToEmpty(columns[2]).trim()),
                                          getDate(nullToEmpty(columns[3]).trim()));
    }

    private static int getAmount(String amount) {
        return Integer.parseInt(amount);
    }

    private static Unit getUnit(String unit) {
        return Unit.valueOf(unit.toUpperCase());
    }

    private static LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("d/M/yyyy");
        return formatter.parseLocalDate(date);
    }
}
