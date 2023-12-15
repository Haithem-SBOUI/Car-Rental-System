package de.tekup.carrentalsystembackend.controller;


import de.tekup.carrentalsystembackend.dto.LoginDto;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.CarBrand;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import de.tekup.carrentalsystembackend.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("localhost:4200")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add-vehicle/{idUser}")
    public ResponseEntity<?> addVehicle(@PathVariable Long idUser, @RequestBody VehicleDto vehicleDto) {

        try {
            vehicleService.addVehicle(idUser, vehicleDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityNotFoundException e) {
            // exception if the email or username already exists
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
        } catch (Exception e) {
            // any other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verify The User Data");
        }
    }


    @GetMapping("/find-vehicle-by-id/{id}")
    public Vehicle findVehicleById(@PathVariable Long id) {
        return vehicleService.findVehicleById(id);
    }

    @GetMapping("/get-all-vehicle")
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    //    todo: filter methods:
//    todo: findAllByBrand
    @GetMapping("/get-all-vehicle-by-brand/{brand}")
    public ResponseEntity<?> findAllByBrand(@PathVariable CarBrand brand) {
        Optional<List<Vehicle>> vehicles = vehicleService.findAllByBrand(brand);
        if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
            return ResponseEntity.ok().body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //    todo: findAllByBrandAndModel
    @GetMapping("/get-all--by-brand-and-model/{brand}/{model}")
    public ResponseEntity<?> findAllByBrandAndModel(@PathVariable CarBrand brand, @PathVariable String model) {
        Optional<List<Vehicle>> vehicles = vehicleService.findAllByBrandAndModel(brand, model);
        if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
            return ResponseEntity.ok().body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //    todo: findAllByFuel
    @GetMapping("/get-all-vehicle-by-fuel/{fuel}")
    public ResponseEntity<?> findAllByFuel(@PathVariable String fuel) {
        Optional<List<Vehicle>> vehicles = vehicleService.findAllByFuel(fuel);
        if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
            return ResponseEntity.ok().body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    todo: findAllByColor
//    todo: findAllByTransmissionType
//    todo: findAllByPriceRange
//    todo: findAllByAvailability


}
