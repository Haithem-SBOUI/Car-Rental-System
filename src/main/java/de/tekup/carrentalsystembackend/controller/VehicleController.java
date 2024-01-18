package de.tekup.carrentalsystembackend.controller;


import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.*;
import de.tekup.carrentalsystembackend.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
@CrossOrigin(origins = "http://localhost:4200/")
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

       /* } catch (AccessDeniedException e) {

            return ResponseEntity.status(403).body(e.getMessage());*/
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
            return  ResponseEntity.ok(vehicles);

        }catch (Exception e)
        {
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
    @PutMapping("/updateVehicle/{id}")
    public Vehicle updateVehicle(@PathVariable Long idVehicle, @RequestBody Vehicle vehicle) {
        Vehicle v1 = vehicleService.getVehicleById(idVehicle);
        if (v1 != null) {
            vehicle.setIdVehicle(idVehicle);
            return vehicleService.updateVehicle(vehicle);
        } else {
            throw new RuntimeException("Fail update vehicle not found !!");
        }
    }

    //Delete Vehicle By Id
    @DeleteMapping("/deleteVehicle/{id}")
    public HashMap<String, String> deleteVehicle(@PathVariable Long id) {
        HashMap<String, String> deletStat = new HashMap<>();
        if (vehicleService.getVehicleById(id) == null) {
            deletStat.put("Status -->", " Error Vehicle Not Found !!!!");

            return deletStat;
        }
        try {
            vehicleService.getVehicleById(id);
            deletStat.put("Status -->", "Successfully Deleting");
            return deletStat;
        } catch (Exception e) {
            deletStat.put("Status -->", " Error Vehicle Not deleted");
            return deletStat;
        }
    }


}
