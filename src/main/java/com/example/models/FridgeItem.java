package com.example.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class FridgeItem {

    private String item;
    private int amount;
    private Unit unit;
    private Date useBy;

}
