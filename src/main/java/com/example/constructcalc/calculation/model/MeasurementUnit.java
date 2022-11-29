package com.example.constructcalc.calculation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="measurement_unit")
public class MeasurementUnit {
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

    public MeasurementUnit()
    {

    }
    public MeasurementUnit(String name) {
        this.name = name;
    }
}
