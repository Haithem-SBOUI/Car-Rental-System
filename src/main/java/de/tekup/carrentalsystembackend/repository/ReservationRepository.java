package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.Reservation;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import de.tekup.carrentalsystembackend.model.enums.ReservationStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    public boolean findBy
//@Query("select r from Reservation r where r.pickupDate <= ?1 and r.returnDate >= ?2")
//List<Reservation> findAllByPickupDateIsLessThanEqualAndReturnDateGreaterThanEqual()

    @Query("SELECT MONTH(r.pickupDate), COUNT(r) FROM Reservation r GROUP BY MONTH(r.pickupDate)")
    List<Object[]> getChartData();
    Reservation findByIdAndStatus(Long id, ReservationStatusEnum status);

    boolean existsByIdAndStatus(Long id, ReservationStatusEnum status);

    List<Reservation> findByStatus(ReservationStatusEnum status);


    List<Reservation> findByUser(User user);
    boolean existsByVehicleAndPickupDateLessThanEqualAndReturnDateGreaterThanEqual(Vehicle vehicle, LocalDate pickupDate, LocalDate returnDate);
}
