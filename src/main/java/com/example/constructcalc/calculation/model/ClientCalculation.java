package com.example.constructcalc.calculation.model;

import com.example.constructcalc.client.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ClientCalculation")
public class ClientCalculation {
    @SequenceGenerator(
            name = "idSequenceGenerator",
            sequenceName = "idSequenceGenerator",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "idSequenceGenerator"
    )
    private Long id;

    @Column(name = "objectAddress")
    private String objectAddress;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client", referencedColumnName = "id")
    @JsonIgnore
    private Client client;

    public ClientCalculation(String objectAddress, String status, Client client) {
        this.objectAddress = objectAddress;
        this.status = status;
        this.client = client;
    }

    public ClientCalculation(){

    }
}
