package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.CarBrand;
import de.tekup.carrentalsystembackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findVehicleByIdVehicle(Long id);

    Optional<List<Vehicle>> findAllByBrand(CarBrand brand);

    Optional<List<Vehicle>> findAllByBrandAndModel(CarBrand brand, String model);

    Optional<List<Vehicle>> findAllByFuel(String fuel);
}
