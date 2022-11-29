package com.example.constructcalc.calculation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="calculationResult")
public class CalculationResult {
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

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="materialCharacteristic", referencedColumnName = "id")
    private MaterialCharacteristic materialCharacteristic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="calculation", referencedColumnName = "id")
    private ClientCalculation calculation;

    @Column(name = "count")
    private double count;

    @Column(name = "price")
    private double price;

    public CalculationResult(String name, MaterialCharacteristic materialCharacteristic, ClientCalculation calculation, double count, double price) {
        this.name = name;
        this.materialCharacteristic = materialCharacteristic;
        this.calculation = calculation;
        this.count = count;
        this.price = price;
    }

    public CalculationResult() {
    }
}
