package com.example.constructcalc.calculation.repositories;

import com.example.constructcalc.calculation.model.Material;
import com.example.constructcalc.calculation.model.MaterialCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByName(String name);
}
