package com.example.constructcalc.client.payload;

import com.example.constructcalc.calculation.model.ClientCalculation;
import com.example.constructcalc.client.model.Client;

import java.util.List;

public class ClientInfo {
    private Client client;
    private List<ClientCalculation> calculationList;

    public ClientInfo(Client client, List<ClientCalculation> calculationList) {
        this.client = client;
        this.calculationList = calculationList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ClientCalculation> getCalculationList() {
        return calculationList;
    }

    public void setCalculationList(List<ClientCalculation> calculationList) {
        this.calculationList = calculationList;
    }
}
