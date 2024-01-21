package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.dto.ReservationCreationRequestDto;
import de.tekup.carrentalsystembackend.model.Reservation;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.UserRole;
import de.tekup.carrentalsystembackend.model.Vehicle;
import de.tekup.carrentalsystembackend.repository.ReservationRepository;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import de.tekup.carrentalsystembackend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;


    public Reservation addReservation(ReservationCreationRequestDto reservationDto) {
        Optional<User> user = userRepository.findById(reservationDto.getUserId());
        Optional<Vehicle> vehicle = vehicleRepository.findById(reservationDto.getVehicleId());

        if (user.isPresent() && vehicle.isPresent()) {
            Reservation reservation = convertDtoToEntity(user.get(), vehicle.get(), reservationDto);
            return reservationRepository.save(reservation);
        } else if (user.isEmpty()) {
            throw new RuntimeException("user not found!!");
        } else {
            throw new RuntimeException("Vehicle not found!!");
        }
    }


    private Reservation convertDtoToEntity(User user, Vehicle vehicle, ReservationCreationRequestDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setVehicle(vehicle);
        reservation.setPickupDate(reservationDto.getPickupDate());
        reservation.setReturnDate(reservationDto.getReturnDate());
        reservation.setTotalCost(reservationDto.getTotalCost());
        if (user.getRole().equals(UserRole.ROLE_ADMIN)) {
            reservation.setStatus("APPROVED");
        } else {
            reservation.setStatus("PENDING");
        }
        return reservation;
    }
}
