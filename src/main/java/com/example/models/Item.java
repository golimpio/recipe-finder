package com.example.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {

    private String item;
    private int amount;
    private Unit unit;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item='" + item + '\'' +
                ", amount=" + amount +
                ", unit=" + unit +
                '}';
    }
}
