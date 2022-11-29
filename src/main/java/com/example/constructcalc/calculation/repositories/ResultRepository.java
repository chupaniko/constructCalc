package com.example.constructcalc.calculation.repositories;

import com.example.constructcalc.calculation.model.CalculationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<CalculationResult, Long> {
}
