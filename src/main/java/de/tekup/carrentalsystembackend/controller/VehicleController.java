package de.tekup.carrentalsystembackend.controller;


import de.tekup.carrentalsystembackend.dto.LoginDto;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
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
    public ResponseEntity<?> addVehicle(@PathVariable Long idUser, @RequestBody VehicleDto vehicleDto){

        try{
            vehicleService.addVehicle(idUser, vehicleDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (EntityNotFoundException e){
            // exception if the email or username already exists
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
        }catch (Exception e){
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



}
