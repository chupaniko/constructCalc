package com.example.constructcalc.calculation.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name="materialCharacteristic")
public class MaterialCharacteristic {
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

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "length")
    private double length;

    @Column(name = "volume")
    private double volume;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="material", referencedColumnName = "id")
    private Material material;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="unit", referencedColumnName = "id")
    private MeasurementUnit unit;


    public MaterialCharacteristic(String name, double width, double height, double length, double volume, double price, Material material, MeasurementUnit unit) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.length = length;
        this.volume = volume;
        this.price = price;
        this.material = material;
        this.unit = unit;
    }

    public MaterialCharacteristic(){

    }
}
