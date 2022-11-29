package com.example.constructcalc.calculation.payload;

import com.example.constructcalc.calculation.model.CalculationResult;

import java.util.ArrayList;
import java.util.List;

public class FoundationElement {
    private String name;
    private List<CalculationResult> foundationMaterialElementList;
    private double price;

    public FoundationElement()
    {
        foundationMaterialElementList = new ArrayList<>();
        price = 0.0;
    }

    public FoundationElement(String name, List<CalculationResult> foundationMaterialElementList, double price)
    {
        this.name = name;
        this.foundationMaterialElementList = foundationMaterialElementList;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CalculationResult> getFoundationMaterialElementList() {
        return foundationMaterialElementList;
    }

    public void setFoundationMaterialElementList(List<CalculationResult> foundationMaterialElementList) {
        this.foundationMaterialElementList = foundationMaterialElementList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addFoundationMaterialElement(CalculationResult foundationMaterialElement)
    {
        foundationMaterialElementList.add(foundationMaterialElement);
        price += foundationMaterialElement.getPrice();
    }
}
