package com.example.constructcalc.calculation.repositories;

import com.example.constructcalc.calculation.model.ClientCalculation;
import com.example.constructcalc.calculation.model.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundationRepository extends JpaRepository<Foundation, Long> {
    List<Foundation> findByCalculation(ClientCalculation calculation);
}
