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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        ClientCalculation calculation;

        if (foundationRequest.getCalculation() == null)
            calculation = clientCalculationRepository.save(new ClientCalculation(foundationRequest.getObjectAddress(), "Открыт", clientRepository.findById(foundationRequest.getClient().getId()).get()));
        else
            calculation = clientCalculationRepository.findById(foundationRequest.getCalculation().getId()).get();

        FoundationResult foundationResult = new FoundationResult();
        MaterialCharacteristic concretePiles = materialCharacteristicRepository.findById(foundationRequest.getConcretePiles().getId()).get();
        MaterialCharacteristic concreteMaterial = materialCharacteristicRepository.findById(foundationRequest.getConcrete().getId()).get();

        Foundation foundation;
        boolean isEdit = false;
        List<CalculationResult> results = new ArrayList<>();
        if (foundationRequest.getFoundation() == null)
        {
            foundation = foundationRepository.save(new Foundation(foundationRequest.getExternalWallsPerimeter(), foundationRequest.getInternalWallLength(), concretePiles, concreteMaterial, calculation));
        }
        else
        {
            foundation = foundationRepository.findById(foundationRequest.getFoundation().getId()).get();
            isEdit = true;
            results = resultRepository.findByCalculationAndElementType(calculation, "F");
        }

        //сваи
        FoundationElement piles;
        //количество свай
        CalculationResult pilesCount;
        int countPiles = (foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) / 2;
        double pricePiles = countPiles * concretePiles.getPrice();
        if (isEdit)
        {
            pilesCount = updateCalculationResult(results, "Количество свай", countPiles, pricePiles);
        }
        else {
            pilesCount = new CalculationResult("Количество свай", concretePiles, calculation, countPiles, pricePiles, "F");
        }
        resultRepository.save(pilesCount);
        piles = new FoundationElement("Сваи", Collections.singletonList(pilesCount), pilesCount.getPrice());

        foundationResult.addElement(piles);

        //Ростверк
        FoundationElement rostverk = new FoundationElement();
        //Бетон
        CalculationResult concrete;
        double concreteCount = (foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) * 0.3 * 0.4 * 1.15;
        double contretePrice = concreteCount * concreteMaterial.getPrice();
        if (isEdit)
            concrete = updateCalculationResult(results, "Бетон", concreteCount, contretePrice);
        else
            concrete = new CalculationResult("Бетон", concreteMaterial, calculation, concreteCount, contretePrice, "F");
        resultRepository.save(concrete);
        rostverk.addFoundationMaterialElement(concrete);
        //Арматура 1
        CalculationResult armatura1;
        MaterialCharacteristic armaturaMaterial1 = materialCharacteristicRepository.findByMaterialAndWidth(materialRepository.findByName("Арматура").get(0), 14.0).get(0);
        int armaturaCount1 = ((foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) * 4) / 6;
        double armaturaPrice1 = armaturaCount1 * armaturaMaterial1.getPrice();
        if (isEdit)
            armatura1 = updateCalculationResult(results, "Арматура 1", armaturaCount1, armaturaPrice1);
        else
            armatura1 = new CalculationResult("Арматура 1", armaturaMaterial1, calculation, armaturaCount1, armaturaPrice1, "F");
        resultRepository.save(armatura1);
        rostverk.addFoundationMaterialElement(armatura1);
        //Арматура 2
        CalculationResult armatura2;
        MaterialCharacteristic armaturaMaterial2 = materialCharacteristicRepository.findByMaterialAndWidth(materialRepository.findByName("Арматура").get(0), 8.0).get(0);
        int armaturaCount2 = (int)(((foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) / 0.3) * (0.2 + 0.3) * 2.0 / 6.0);
        double armaturaPrice2 = armaturaCount2 * armaturaMaterial2.getPrice();
        if (isEdit)
            armatura2 = updateCalculationResult(results, "Арматура 2", armaturaCount2, armaturaPrice2);
        else
            armatura2 = new CalculationResult("Арматура 2", armaturaMaterial1, calculation, armaturaCount2, armaturaPrice1, "F");
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
        if (isEdit)
            doska = updateCalculationResult(results, "Доска", doskaCount, doskaPrice);
        else
            doska = new CalculationResult("Доска", doskaMaterial, calculation, doskaCount, doskaPrice, "F");
        resultRepository.save(doska);
        opalubka.addFoundationMaterialElement(doska);
        //брус
        CalculationResult brus;
        MaterialCharacteristic brusMaterial = materialCharacteristicRepository.findByMaterialAndWidthAndHeightAndLength(materialRepository.findByName("Брус").get(0), 50.0, 50.0, 3000.0).get(0);
        double brusCount = ((foundationRequest.getExternalWallsPerimeter() + foundationRequest.getInternalWallLength()) * 2) / 0.7 * 0.5 * 0.05 * 0.05;
        double brusPrice = brusCount * brusMaterial.getPrice();
        if (isEdit)
            brus = updateCalculationResult(results, "Брус", brusCount, brusPrice);
        else
            brus = new CalculationResult("Брус", brusMaterial, calculation, brusCount, brusPrice, "F");
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

    @GetMapping("/getBetonValues")
    public ResponseEntity<List<MaterialCharacteristic>> getBetonValues(){
        Material beton = materialRepository.findByName("Бетон").get(0);
        return new ResponseEntity<>(materialCharacteristicRepository.findByMaterial(beton), HttpStatus.OK);
    }

    @GetMapping("/getBetonPilesValues")
    public ResponseEntity<List<MaterialCharacteristic>> getBetonPiles(){
        Material betonPiles = materialRepository.findByName("Бетонные сваи").get(0);
        return new ResponseEntity<>(materialCharacteristicRepository.findByMaterial(betonPiles), HttpStatus.OK);
    }

    @GetMapping("/getFoundationByCalculation/{id}")
    public ResponseEntity<Foundation> findFoundationByCalculation(@PathVariable(name = "id") long calculationId){
        return new ResponseEntity<>(foundationRepository.findByCalculation(clientCalculationRepository.findById(calculationId).get()).get(0), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCalculation/{id}")
    public ResponseEntity<List<ClientCalculation>> deleteCalculation(@PathVariable(name = "id") long calculationId){
        ClientCalculation calculation = clientCalculationRepository.findById(calculationId).get();

        //cascading
        foundationRepository.deleteAll(foundationRepository.findByCalculation(calculation));
        resultRepository.deleteAll(resultRepository.findByCalculation(calculation));

        clientCalculationRepository.delete(calculation);

        return new ResponseEntity<>(clientCalculationRepository.findAll(), HttpStatus.OK);
    }

    private CalculationResult updateCalculationResult(List<CalculationResult> results, String name, double count, double price)
    {
        CalculationResult result = results.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList()).get(0);
        result.setCount(count);
        result.setPrice(price);

        return result;
    }
}
