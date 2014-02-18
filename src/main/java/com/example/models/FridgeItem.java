package com.example.models;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

public class FridgeItem extends Item {

    private LocalDate useBy;

    public FridgeItem(String item, int amount, Unit unit, LocalDate useBy) {
        setItem(item);
        setAmount(amount);
        setUnit(unit);
        setUseBy(useBy);
    }

    public LocalDate getUseBy() {
        return useBy;
    }

    public void setUseBy(LocalDate useBy) {
        this.useBy = useBy;
    }

    @Override
    public String toString() {
        return "FridgeItem{" + super.toString() +
                ", useBy=" + useBy +
                '}';
    }
}
