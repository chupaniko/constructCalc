package com.example.constructcalc;

import com.example.constructcalc.calculation.model.Material;
import com.example.constructcalc.calculation.model.MaterialCharacteristic;
import com.example.constructcalc.calculation.model.MeasurementUnit;
import com.example.constructcalc.calculation.repositories.MaterialCharacteristicRepository;
import com.example.constructcalc.calculation.repositories.MaterialRepository;
import com.example.constructcalc.calculation.repositories.MeasurementUnitRepository;
import com.example.constructcalc.user.AppUser;
import com.example.constructcalc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {

    }


}
