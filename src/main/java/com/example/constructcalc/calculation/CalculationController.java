package com.example.constructcalc.calculation;

import com.example.constructcalc.calculation.model.*;
import com.example.constructcalc.calculation.payload.FoundationElement;
import com.example.constructcalc.calculation.payload.FoundationRequest;
import com.example.constructcalc.calculation.payload.FoundationResult;
import com.example.constructcalc.calculation.repositories.*;
import com.example.constructcalc.client.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/calculation")
public class CalculationController {
    MaterialRepository materialRepository;
    MaterialCharacteristicRepository materialCharacteristicRepository;
    MeasurementUnitRepository measurementUnitRepository;
    ClientRepository clientRepository;
    ClientCalculationRepository clientCalculationRepository;
    ResultRepository resultRepository;
    FoundationRepository foundationRepository;

    public CalculationController(MaterialRepository materialRepository,
                                 MaterialCharacteristicRepository materialCharacteristicRepository,
                                 MeasurementUnitRepository measurementUnitRepository,
                                 ClientCalculationRepository clientCalculationRepository,
                                 ClientRepository clientRepository,
                                 ResultRepository resultRepository,
                                 FoundationRepository foundationRepository)
    {
        this.materialRepository = materialRepository;
        this.materialCharacteristicRepository = materialCharacteristicRepository;
        this.measurementUnitRepository = measurementUnitRepository;
        this.clientRepository = clientRepository;
        this.clientCalculationRepository = clientCalculationRepository;
        this.resultRepository = resultRepository;
        this.foundationRepository = foundationRepository;
    }

    @PostMapping("/foundation")
    public ResponseEntity<FoundationResult> calculateFoundation(@RequestBody FoundationRequest foundationRequest)
    {
        ClientCalculation calculation = clientCalculationRepository.save(new ClientCalculation(foundationRequest.getObjectAddress(), "Открыт", clientRepository.findById(foundationRequest.getClient().getId()).get()));


        FoundationResult foundationResult = new FoundationResult();
        MaterialCharacteristic concretePiles = materialCharacteristicRepository.findById(foundationRequest.getConcretePiles().getId()).get();
        MaterialCharacteristic concreteMaterial = materialCharacteristicRepository.findById(foundationRequest.getConcrete().getId()).get();

        Foundation foundation = foundationRepository.save(new Foundation(foundationRequest.getExternalWallsPerimeter(), foundationRequest.getInternalWallLength(), concretePiles, concreteMaterial, calculation));

        //сваи
        FoundationElement piles;
        //количество свай
        CalculationResult pilesCount;
        int countPiles = (foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) / 2;
        double pricePiles = countPiles * concretePiles.getPrice();
        pilesCount = new CalculationResult("Количество свай", concretePiles, calculation, countPiles, pricePiles);
        resultRepository.save(pilesCount);
        piles = new FoundationElement("Сваи", Collections.singletonList(pilesCount), pilesCount.getPrice());

        foundationResult.addElement(piles);

        //Ростверк
        FoundationElement rostverk = new FoundationElement();
        //Бетон
        CalculationResult concrete;
        double concreteCount = (foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) * 0.3 * 0.4 * 1.15;
        double contretePrice = concreteCount * concreteMaterial.getPrice();
        concrete = new CalculationResult("Бетон", concreteMaterial, calculation, concreteCount, contretePrice);
        resultRepository.save(concrete);
        rostverk.addFoundationMaterialElement(concrete);
        //Арматура 1
        CalculationResult armatura1;
        MaterialCharacteristic armaturaMaterial1 = materialCharacteristicRepository.findByMaterialAndWidth(materialRepository.findByName("Арматура").get(0), 14.0).get(0);
        int armaturaCount1 = ((foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) * 4) / 6;
        double armaturaPrice1 = armaturaCount1 * armaturaMaterial1.getPrice();
        armatura1 = new CalculationResult("Арматура", armaturaMaterial1, calculation, armaturaCount1, armaturaPrice1);
        resultRepository.save(armatura1);
        rostverk.addFoundationMaterialElement(armatura1);
        //Арматура 2
        CalculationResult armatura2;
        MaterialCharacteristic armaturaMaterial2 = materialCharacteristicRepository.findByMaterialAndWidth(materialRepository.findByName("Арматура").get(0), 8.0).get(0);
        int armaturaCount2 = (int)(((foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) / 0.3) * (0.2 + 0.3) * 2.0 / 6.0);
        double armaturaPrice2 = armaturaCount2 * armaturaMaterial2.getPrice();
        armatura2 = new CalculationResult("Арматура", armaturaMaterial1, calculation, armaturaCount2, armaturaPrice1);
        resultRepository.save(armatura2);
        rostverk.addFoundationMaterialElement(armatura2);

        foundationResult.addElement(rostverk);

        //Опалубка
        FoundationElement opalubka = new FoundationElement();
        //Доска
        CalculationResult doska;
        MaterialCharacteristic doskaMaterial = materialCharacteristicRepository.findByMaterialAndWidthAndHeightAndLength(materialRepository.findByName("Доска").get(0), 30.0, 100.0, 3000.0).get(0);
        double doskaCount = (foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) * 2 * (0.4 + 0.1) * 0.03;
        double doskaPrice = doskaCount * doskaMaterial.getPrice();
        doska = new CalculationResult("Доска", doskaMaterial, calculation, doskaCount, doskaPrice);
        resultRepository.save(doska);
        opalubka.addFoundationMaterialElement(doska);
        //брус
        CalculationResult brus;
        MaterialCharacteristic brusMaterial = materialCharacteristicRepository.findByMaterialAndWidthAndHeightAndLength(materialRepository.findByName("Брус").get(0), 50.0, 50.0, 3000.0).get(0);
        double brusCount = ((foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) * 2) / 0.7 * 0.5 * 0.05 * 0.05;
        double brusPrice = brusCount * brusMaterial.getPrice();
        brus = new CalculationResult("Брус", brusMaterial, calculation, brusCount, brusPrice);
        resultRepository.save(brus);
        opalubka.addFoundationMaterialElement(brus);

        foundationResult.addElement(opalubka);

        return new ResponseEntity<>(foundationResult, HttpStatus.OK);
    }

    @GetMapping("/getMaterialCharacteristics/{materialId}")
    public ResponseEntity<List<MaterialCharacteristic>> getMaterialCharacteristics(@PathVariable(name = "materialId") Long materialId)
    {
        Material material = materialRepository.getReferenceById(materialId);
        List<MaterialCharacteristic> materialCharacteristics = materialCharacteristicRepository.findByMaterial(material);

        return new ResponseEntity<>(materialCharacteristics, HttpStatus.OK);
    }

    @GetMapping("/autocompleteMaterials")
    public ResponseEntity<List<MaterialCharacteristic>> autocompleteMaterials(){
        /*MeasurementUnit sht = measurementUnitRepository.save(new MeasurementUnit(1L, "шт"));
        MeasurementUnit m3 = measurementUnitRepository.save(new MeasurementUnit(2L, "м3"));
        MeasurementUnit m = measurementUnitRepository.save(new MeasurementUnit(3L, "м"));

        Material svai = materialRepository.save(new Material(1L, "Бетонные сваи"));
        materialCharacteristicRepository.save(new MaterialCharacteristic(1L, "", 150.0, 150.0, 3000.0, 0, 1500, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic(2L, "", 200.0, 200.0, 3000.0, 0, 1900, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic(3L, "", 300.0, 300.0, 3000.0, 0, 3383, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic(4L, "", 300.0, 300.0, 4000.0, 0, 4346, svai, sht));
        materialCharacteristicRepository.save(new MaterialCharacteristic(5L, "", 300.0, 300.0, 5000.0, 0, 4953, svai, sht));

        Material beton = materialRepository.save(new Material(2L, "Бетон"));
        materialCharacteristicRepository.save(new MaterialCharacteristic(6L, "М150(В10)", 0, 0, 0, 0, 2760, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(7L, "М200(В15)", 0, 0, 0, 0, 2910, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(8L, "М250(В20)", 0, 0, 0, 0, 3100, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(9L, "М300 (В22.5)", 0, 0, 0, 0, 3160, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(10L, "М350(В25)", 0, 0, 0, 0, 3260, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(11L, "М400(В30)", 0, 0, 0, 0, 3460, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(12L, "М450(В35)", 0, 0, 0, 0, 3680, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(13L, "М500(В40)", 0, 0, 0, 0, 3760, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(13L, "М550(В45)", 0, 0, 0, 0, 4135, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(13L, "М600(В50)", 0, 0, 0, 0, 4335, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(13L, "М700(В55)", 0, 0, 0, 0, 4680, beton, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(13L, "М800(В65)", 0, 0, 0, 0, 4620, beton, m3));

        Material armatura = materialRepository.save(new Material(3L, "Арматура"));
        materialCharacteristicRepository.save(new MaterialCharacteristic(14L, "", 8, 0, 0, 0, 23, armatura, m));
        materialCharacteristicRepository.save(new MaterialCharacteristic(15L, "", 14, 0, 0, 0, 23, armatura, m));

        Material doska = materialRepository.save(new Material(4L, "Доска"));
        materialCharacteristicRepository.save(new MaterialCharacteristic(16L, "", 30.0, 100.0, 3000.0, 0, 7800, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(17L, "", 50.0, 100.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(18L, "", 50.0, 150.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(19L, "", 50.0, 200.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(20L, "", 50.0, 250.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(21L, "", 50.0, 300.0, 3000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(22L, "", 50.0, 100.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(23L, "", 50.0, 150.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(24L, "", 50.0, 200.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(25L, "", 50.0, 250.0, 6000.0, 0, 12000, doska, m3));
        materialCharacteristicRepository.save(new MaterialCharacteristic(26L, "", 50.0, 300.0, 6000.0, 0, 12000, doska, m3));

        Material brus = materialRepository.save(new Material(5L, "Брус"));
        materialCharacteristicRepository.save(new MaterialCharacteristic(27L, "", 50.0, 50.0, 3000.0, 0, 9100, brus, m3));

        return new ResponseEntity<>(materialCharacteristicRepository.findAll(), HttpStatus.OK);*/
        return null;
    }
}
