package com.example.constructcalc.calculation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="material")
public class Material {
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

    public Material(String name)
    {
        this.name = name;
    }

    public Material(){

    }
}
