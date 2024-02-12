package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import de.tekup.carrentalsystembackend.model.enums.FuelType;
import de.tekup.carrentalsystembackend.model.enums.TransmType;
import de.tekup.carrentalsystembackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT c FROM Vehicle c JOIN c.reservations r " +
            "WHERE " +
            "(:pickupDate IS NULL OR :pickupDate NOT BETWEEN r.pickupDate AND r.returnDate) AND " +
            "(:brand IS NULL OR c.brand = :brand) AND " +
            "(:model IS NULL OR c.model = :model) AND " +
            "(:maxPrice IS NULL OR c.pricePerDay <= :maxPrice)")
    List<Vehicle> findByFilters(
            @Param("pickupDate") LocalDate pickupDate,
            @Param("brand") CarBrand brand,
            @Param("model") String model,
            @Param("maxPrice") Long maxPrice);



    Optional<Vehicle> findVehicleById(Long id);

    Optional<List<Vehicle>> findAllByBrand(CarBrand brand);

    Optional<List<Vehicle>> findAllByBrandAndModel(CarBrand brand, String model);

    Optional<List<Vehicle>> findAllByFuel(FuelType fuel);

    //Optional<List<Vehicle>> findAllByTransType(String transmissionType);

    Optional<List<Vehicle>> findAllByHorsPowerBetween(int min, int max);

    Optional<List<Vehicle>> findAllByPricePerDayBetween(int min, int max);

    Optional<List<Vehicle>> findAllByIsAvailable(boolean isAvailable);

    Optional<List<Vehicle>> findAllByTransmissionType(TransmType transmissionType);
}
