package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.core.exception.type.BadParameterException;
import de.tekup.carrentalsystembackend.core.exception.type.NotFoundException;
import de.tekup.carrentalsystembackend.core.exception.type.UnauthorizedException;
import de.tekup.carrentalsystembackend.dto.ReservationCreationRequestDto;
import de.tekup.carrentalsystembackend.dto.ReservationDto;
import de.tekup.carrentalsystembackend.dto.modelMapper.ReservationMapper;
import de.tekup.carrentalsystembackend.model.Reservation;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.enums.UserRole;
import de.tekup.carrentalsystembackend.model.Vehicle;
import de.tekup.carrentalsystembackend.model.enums.ReservationStatusEnum;
import de.tekup.carrentalsystembackend.repository.ReservationRepository;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import de.tekup.carrentalsystembackend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final ReservationMapper reservationMapper;


    public Reservation createReservation(ReservationDto reservationDto) {
        userRepository.findById(reservationDto.getUser().getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        vehicleRepository.findById(reservationDto.getVehicle().getId())
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        Reservation reservation = reservationMapper.toEntity(reservationDto);
        return reservationRepository.save(reservation);

    }


    public List<ReservationDto> findReservationByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        List<Reservation> reservations = reservationRepository.findByUser(user);
        if (!reservations.isEmpty()) {
            return reservationMapper.toDtoList(reservations);
        } else {
            return Collections.emptyList();
        }
    }


    public ReservationDto findReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        return reservationMapper.toDto(reservation);
    }

    public void deleteReservationById(Long userId, Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));

        if (reservation.getUser().getId().equals(userId) || reservation.getUser().getRole().equals(UserRole.ROLE_ADMIN)) {
            reservationRepository.deleteById(id);
        } else {
            throw new UnauthorizedException("Unauthorized Action");
        }
    }

    public List<ReservationDto> findAllReservation() {
        List<Reservation> allReservation = reservationRepository.findAll();
        return reservationMapper.toDtoList(allReservation);

    }


    public ReservationDto changeReservationStatus(Long id, String status) {
        ReservationStatusEnum newStatus = ReservationStatusEnum.valueOf(status.toUpperCase());

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation Not Found"));

        if (isValidTransition(reservation.getStatus(), newStatus)) {
            reservation.setStatus(newStatus);
            return reservationMapper.toDto(reservationRepository.save(reservation));
        } else {
            throw new BadParameterException("Invalid status transition from " + reservation.getStatus() + " to " + status);
        }

    }

    private boolean isValidTransition(ReservationStatusEnum currentStatus, ReservationStatusEnum newStatus) {
        return switch (newStatus) {
            case CANCELED ->
                    currentStatus.equals(ReservationStatusEnum.PENDING) || currentStatus.equals(ReservationStatusEnum.CONFIRMED);
            case PENDING ->
                    currentStatus.equals(ReservationStatusEnum.CANCELED) || currentStatus.equals(ReservationStatusEnum.CONFIRMED);
            case CONFIRMED ->
                    currentStatus.equals(ReservationStatusEnum.CANCELED) || currentStatus.equals(ReservationStatusEnum.PENDING);
            case PAYED -> currentStatus.equals(ReservationStatusEnum.CONFIRMED);
            case COMPLETED -> currentStatus.equals(ReservationStatusEnum.PAYED);
            default -> false;
        };
    }


}


