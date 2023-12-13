package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import de.tekup.carrentalsystembackend.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;


    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findVehicleByIdVehicle(id).orElse(null);
    }
    public void addVehicle(Long idUser, VehicleDto vehicleDto) {
        // Retrieve the user from the database
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Create new vehicle instance from the dto
        Vehicle vehicle =  convertDtoToInstance(vehicleDto);
        vehicle.setUser(user);
        vehicleRepository.save(vehicle);

        // Add the vehicle to the user's list of vehicles
        user.getVehicles().add(vehicle);
        userRepository.save(user);

    }

    private Vehicle convertDtoToInstance(VehicleDto vehicleDto) {
        Vehicle vehicle= new Vehicle();
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
        vehicle.setPricePerDay(vehicleDto.getPricePerDay());
        vehicle.setFuel(vehicleDto.getFuel());
        vehicle.setIsAvailable(vehicleDto.getIsAvailable());
        return vehicle;
    }

    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }
}
