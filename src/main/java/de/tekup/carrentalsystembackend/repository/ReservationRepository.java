package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.Reservation;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    public boolean findBy
//@Query("select r from Reservation r where r.pickupDate <= ?1 and r.returnDate >= ?2")
//List<Reservation> findAllByPickupDateIsLessThanEqualAndReturnDateGreaterThanEqual()

    List<Reservation> findByUser(User user);
    boolean existsByVehicleAndPickupDateLessThanEqualAndReturnDateGreaterThanEqual(Vehicle vehicle, LocalDate pickupDate, LocalDate returnDate);
}
