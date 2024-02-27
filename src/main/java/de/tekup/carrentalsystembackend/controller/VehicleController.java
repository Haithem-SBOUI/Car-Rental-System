package de.tekup.carrentalsystembackend.controller;


import de.tekup.carrentalsystembackend.dto.StringToJsonDto;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.*;
import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import de.tekup.carrentalsystembackend.model.enums.FuelType;
import de.tekup.carrentalsystembackend.model.enums.TransmType;
import de.tekup.carrentalsystembackend.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
@CrossOrigin(origins = "http://localhost:4200/")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add-vehicle/{idUser}")
    public ResponseEntity<?> addVehicle(@PathVariable Long idUser, @ModelAttribute VehicleDto vehicleDto) throws IOException {
        vehicleService.addVehicle(idUser, vehicleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                StringToJsonDto.builder()
                        .message("Vehicle Created Successfully")
                        .build()
        );

    }


    //  filter implementation

    @GetMapping("/filter-vehicle")
    public ResponseEntity<?> filterVehicle(
            @RequestParam(required = false) LocalDate pickupDate,//todo: fix still ignore the first Vehicle
            @RequestParam(required = false) CarBrand brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Long maxPrice) {
        List<VehicleDto> vehicleDtoList = vehicleService.findByFilters(pickupDate, brand, model, maxPrice);
        return ResponseEntity.ok(vehicleDtoList);
    }

    @GetMapping("/all-vehicle-brand")
    public ResponseEntity<CarBrand[]> allVehicleBrand() {
        return ResponseEntity.ok(CarBrand.values());
    }


    @GetMapping("/find-vehicle-by-id/{id}")
    public VehicleDto findVehicleById(@PathVariable Long id) {
        return vehicleService.findVehicleById(id);
    }

    @GetMapping("/get-all-vehicle")
    public ResponseEntity<?> getAllVehicle() {
        List<VehicleDto> publicVehicles = vehicleService.getAllVehicle();
        if (!publicVehicles.isEmpty()) {
            return ResponseEntity.ok(publicVehicles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/find-all-free-vehicle-by-date-time")
    public List<VehicleDto> findAllFreeVehicleByDateTime(@RequestParam String startDate) {
        System.out.println(startDate);
        System.out.println("hey");
        return vehicleService.findAllFreeVehicleByDateTime(startDate);

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

    //    todo: findAllByFuel Should make Enum for Gasoline Diesel Elecetric
    @GetMapping("/get-all-vehicle-by-fuel/{fuel}")
    public ResponseEntity<?> findAllByFuel(@PathVariable FuelType fuel) {
        Optional<List<Vehicle>> vehicles = vehicleService.findAllByFuel(fuel);
        if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
            return ResponseEntity.ok().body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //    todo: findAllHorsPower should be in range between {100 .. 500}
    @GetMapping("/get-all-vehicle-by-horsepower/{minhorsePower}to{maxhorsepower}")
    public ResponseEntity<?> findAllByHorsPower(@PathVariable int minhorsePower, @PathVariable int maxhorsepower) {

        try {
            List<Vehicle> vehicles = vehicleService.findAllByHorsPower(minhorsePower, maxhorsepower).get();
            if (vehicles.isEmpty()) {
                return new ResponseEntity<List<Vehicle>>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(vehicles);

        } catch (Exception e) {
            return new ResponseEntity<List<Vehicle>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo: findAllByTransmissionType should be Manual or Automatic
    @GetMapping("/get-all-vehicle-by-transmission/{transmissionType}")
    public ResponseEntity<?> findAllByTransType(@PathVariable TransmType transmissionType) {
        Optional<List<Vehicle>> vehicles = vehicleService.findAllByTransType(transmissionType);
        if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
            return ResponseEntity.ok().body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //    todo: findAllByPriceRange
    @GetMapping("/get-all-vehicle-by-price/{min_price_per_day}to{max_price_per_day}")
    public ResponseEntity<?> findAllByPrice(@PathVariable int min_price_per_day, @PathVariable int max_price_per_day) {
        Optional<List<Vehicle>> vehicles = vehicleService.findAllByPrice(min_price_per_day, max_price_per_day);
        if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
            return ResponseEntity.ok().body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //    todo: findAllByAvailability
    @GetMapping("/get-all-vehicle-by-avaibility/{is_available}")
    public ResponseEntity<?> findAllByAvailability(@PathVariable boolean is_available) {
        Optional<List<Vehicle>> vehicles = vehicleService.findAllByAvailability(is_available);
        if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
            return ResponseEntity.ok().body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Update Vehicle by Id
    @PutMapping("/update-vehicle-by-id")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.ok().body(
                StringToJsonDto.builder()
                        .message(vehicleService.updateVehicle(vehicleDto))
                        .build());
    }

    //Delete Vehicle By Id
    @DeleteMapping("/delete-vehicle-by-id/{userId}/{id}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable Long userId, @PathVariable Long id) {
        vehicleService.deleteVehicleById(userId, id);
        return ResponseEntity.noContent().build();
    }


}
