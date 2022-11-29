package com.example.constructcalc.calculation.repositories;

import com.example.constructcalc.calculation.model.Material;
import com.example.constructcalc.calculation.model.MaterialCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialCharacteristicRepository extends JpaRepository<MaterialCharacteristic, Long> {
    List<MaterialCharacteristic> findByMaterial(Material material);
    List<MaterialCharacteristic> findByMaterialAndWidth(Material material, double width);
    List<MaterialCharacteristic> findByMaterialAndWidthAndHeightAndLength(Material material, double width, double heigth, double length);
}
