package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import de.tekup.carrentalsystembackend.model.enums.FuelType;
import de.tekup.carrentalsystembackend.model.enums.TransmType;
import de.tekup.carrentalsystembackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findVehicleById(Long id);

    Optional<List<Vehicle>> findAllByBrand(CarBrand brand);

    Optional<List<Vehicle>> findAllByBrandAndModel(CarBrand brand, String model);

    Optional<List<Vehicle>> findAllByFuel(FuelType fuel);

    //Optional<List<Vehicle>> findAllByTransType(String transmissionType);

    Optional<List<Vehicle>> findAllByHorsPowerBetween(int min, int max);

    Optional<List<Vehicle>> findAllByPricePerDayBetween(int min , int max);

    Optional<List<Vehicle>> findAllByIsAvailable(boolean isAvailable);

    Optional<List<Vehicle>> findAllByTransmissionType(TransmType transmissionType);
}
