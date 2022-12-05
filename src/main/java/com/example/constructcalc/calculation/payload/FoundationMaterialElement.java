package com.example.constructcalc.calculation.payload;

import com.example.constructcalc.calculation.model.MaterialCharacteristic;

public class FoundationMaterialElement {
    private String name;
    private MaterialCharacteristic materialCharacteristic;
    private double count;
    private double price;

    public FoundationMaterialElement(){
    }

    public FoundationMaterialElement(String name, MaterialCharacteristic materialCharacteristic, double count, double price) {
        this.name = name;
        this.materialCharacteristic = materialCharacteristic;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MaterialCharacteristic getMaterialCharacteristic() {
        return materialCharacteristic;
    }

    public void setMaterialCharacteristic(MaterialCharacteristic materialCharacteristic) {
        this.materialCharacteristic = materialCharacteristic;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
