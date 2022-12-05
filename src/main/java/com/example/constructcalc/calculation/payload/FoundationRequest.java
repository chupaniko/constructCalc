package com.example.constructcalc.calculation.payload;

import com.example.constructcalc.calculation.model.ClientCalculation;
import com.example.constructcalc.calculation.model.Foundation;
import com.example.constructcalc.calculation.model.MaterialCharacteristic;
import com.example.constructcalc.client.model.Client;

public class FoundationRequest {
    private int externalWallsPerimeter;
    private int internalWallLength;
    private MaterialCharacteristic concretePiles;
    private MaterialCharacteristic concrete;
    private Client client;
    private String objectAddress;
    private ClientCalculation calculation;
    private Foundation foundation;

    public FoundationRequest(int externalWallsPerimeter,
                             int internalWallLength,
                             MaterialCharacteristic concretePiles,
                             MaterialCharacteristic concrete,
                             Client client,
                             String objectAddress,
                             ClientCalculation calculation,
                             Foundation foundation) {
        this.externalWallsPerimeter = externalWallsPerimeter;
        this.internalWallLength = internalWallLength;
        this.concretePiles = concretePiles;
        this.concrete = concrete;
        this.client = client;
        this.objectAddress = objectAddress;
        this.calculation = calculation;
        this.foundation = foundation;
    }

    public int getExternalWallsPerimeter() {
        return externalWallsPerimeter;
    }

    public void setExternalWallsPerimeter(int externalWallsPerimeter) {
        this.externalWallsPerimeter = externalWallsPerimeter;
    }

    public int getInternalWallLength() {
        return internalWallLength;
    }

    public void setInternalWallLength(int internalWallLength) {
        this.internalWallLength = internalWallLength;
    }

    public MaterialCharacteristic getConcretePiles() {
        return concretePiles;
    }

    public void setConcretePiles(MaterialCharacteristic concretePiles) {
        this.concretePiles = concretePiles;
    }

    public MaterialCharacteristic getConcrete() {
        return concrete;
    }

    public void setConcrete(MaterialCharacteristic concrete) {
        this.concrete = concrete;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getObjectAddress() {
        return objectAddress;
    }

    public void setObjectAddress(String objectAddress) {
        this.objectAddress = objectAddress;
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
