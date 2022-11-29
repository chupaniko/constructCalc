package com.example.constructcalc.calculation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="foundation")
public class Foundation {
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

    @Column(name = "externalWallsPerimeter")
    private int externalWallsPerimeter;

    @Column(name = "internalWallLength")
    private int internalWallLength;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="concretePiles", referencedColumnName = "id")
    private MaterialCharacteristic concretePiles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="concrete", referencedColumnName = "id")
    private MaterialCharacteristic concrete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="calculation", referencedColumnName = "id")
    private ClientCalculation calculation;

    public Foundation(int externalWallsPerimeter, int internalWallLength, MaterialCharacteristic concretePiles, MaterialCharacteristic concrete, ClientCalculation calculation) {
        this.externalWallsPerimeter = externalWallsPerimeter;
        this.internalWallLength = internalWallLength;
        this.concretePiles = concretePiles;
        this.concrete = concrete;
        this.calculation = calculation;
    }

    public Foundation(){

    }
}
