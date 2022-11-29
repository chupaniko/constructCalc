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
    @Autowired
    UserRepository userRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialCharacteristicRepository materialCharacteristicRepository;
    @Autowired
    MeasurementUnitRepository measurementUnitRepository;

    @Override
    public void run(String... args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        List<AppUser> userList = new ArrayList<>() {
            {
                add(new AppUser("ivanov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("ivanov128")));
                add(new AppUser("petrov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("petrov938")));
                add(new AppUser("sidorov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("sidorov238")));
                add(new AppUser("grishin@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("grishin289")));
                add(new AppUser("borisov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("borisov738")));
                add(new AppUser("nikolaev@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("nikolaev929")));
                add(new AppUser("abramov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("abramov264")));
                add(new AppUser("kuznetsov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("kuznetsov181")));
                add(new AppUser("kolobkov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("kolobkov391")));
                add(new AppUser("popov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("popov493")));

            }
        };
        userRepository.saveAll(userList);


        MeasurementUnit sht = measurementUnitRepository.save(new MeasurementUnit("шт"));
        MeasurementUnit m3 = measurementUnitRepository.save(new MeasurementUnit("м3"));
        MeasurementUnit m = measurementUnitRepository.save(new MeasurementUnit( "м"));

        Material svai = materialRepository.save(new Material( "Бетонные сваи"));
        materialCharacteristicRepository.save(new MaterialCharacteristic( "", 150.0, 150.0, 3000.0, 0, 1500, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic( "", 200.0, 200.0, 3000.0, 0, 1900, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 300.0, 300.0, 3000.0, 0, 3383, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 300.0, 300.0, 4000.0, 0, 4346, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 300.0, 300.0, 5000.0, 0, 4953, svai, sht));

        Material beton = materialRepository.save(new Material("Бетон"));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М150(В10)", 0, 0, 0, 0, 2760, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М200(В15)", 0, 0, 0, 0, 2910, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М250(В20)", 0, 0, 0, 0, 3100, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М300 (В22.5)", 0, 0, 0, 0, 3160, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М350(В25)", 0, 0, 0, 0, 3260, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М400(В30)", 0, 0, 0, 0, 3460, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М450(В35)", 0, 0, 0, 0, 3680, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М500(В40)", 0, 0, 0, 0, 3760, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М550(В45)", 0, 0, 0, 0, 4135, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М600(В50)", 0, 0, 0, 0, 4335, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М700(В55)", 0, 0, 0, 0, 4680, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("М800(В65)", 0, 0, 0, 0, 4620, beton, m3));

        Material armatura = materialRepository.save(new Material("Арматура"));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 8, 0, 0, 0, 23, armatura, m));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 14, 0, 0, 0, 23, armatura, m));

        Material doska = materialRepository.save(new Material("Доска"));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 30.0, 100.0, 3000.0, 0, 7800, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 100.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 150.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 200.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 250.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 300.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 100.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 150.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 200.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 250.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 300.0, 6000.0, 0, 12000, doska, m3));

        Material brus = materialRepository.save(new Material("Брус"));
        materialCharacteristicRepository.save(new MaterialCharacteristic("", 50.0, 50.0, 3000.0, 0, 9100, brus, m3));
    }
}
