package com.example.constructcalc.calculation.repositories;

import com.example.constructcalc.calculation.model.ClientCalculation;
import com.example.constructcalc.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientCalculationRepository extends JpaRepository<ClientCalculation, Long> {
    List<ClientCalculation> findByClient(Client client);
}
