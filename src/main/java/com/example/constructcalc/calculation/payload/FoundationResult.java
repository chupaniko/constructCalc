package com.example.constructcalc.calculation.payload;

import com.example.constructcalc.calculation.model.ClientCalculation;
import com.example.constructcalc.calculation.model.Foundation;

import java.util.ArrayList;
import java.util.List;

public class FoundationResult {
    private ClientCalculation calculation;
    private Foundation foundation;
    private List<FoundationElement> elements;
    private double price;

    public FoundationResult()
    {
        elements = new ArrayList<>();
        price = 0;
    }

    public FoundationResult(List<FoundationElement> elements, double price) {
        this.elements = elements;
        this.price = price;
    }

    public List<FoundationElement> getElements() {
        return elements;
    }

    public void setElements(List<FoundationElement> elements) {
        this.elements = elements;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addElement(FoundationElement foundationElement){
        elements.add(foundationElement);
        price += foundationElement.getPrice();
    }

    public ClientCalculation getCalculation() {
        return calculation;
    }

    public void setCalculation(ClientCalculation calculation) {
        this.calculation = calculation;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }
}
