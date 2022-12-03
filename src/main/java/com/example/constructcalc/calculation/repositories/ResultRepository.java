package com.example.constructcalc.calculation.repositories;

import com.example.constructcalc.calculation.model.CalculationResult;
import com.example.constructcalc.calculation.model.ClientCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<CalculationResult, Long> {
    List<CalculationResult> findByCalculation(ClientCalculation calculation);
    List<CalculationResult> findByCalculationAndElementType(ClientCalculation clientCalculation, String elementType);
}
