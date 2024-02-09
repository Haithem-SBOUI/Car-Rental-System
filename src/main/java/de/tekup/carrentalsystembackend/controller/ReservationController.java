package de.tekup.carrentalsystembackend.controller;

import de.tekup.carrentalsystembackend.dto.ReservationCreationRequestDto;
import de.tekup.carrentalsystembackend.dto.ReservationDto;
import de.tekup.carrentalsystembackend.dto.StringToJsonDto;
import de.tekup.carrentalsystembackend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/create-reservation")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto) {
        try {
            return ResponseEntity.ok().body(reservationService.createReservation(reservationDto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/find-all-reservation")
    public ResponseEntity<?> findAllReservation() {
        List<ReservationDto> reservationDtoList = reservationService.findAllReservation();
        if (!reservationDtoList.isEmpty()) {
            return ResponseEntity.ok(reservationDtoList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/find-reservation-by-user-id/{id}")
    public ResponseEntity<?> findReservationByUser(@PathVariable Long id) {
        List<ReservationDto> reservationDtoList = reservationService.findReservationByUser(id);
        if (!reservationDtoList.isEmpty()) {
            return ResponseEntity.ok(reservationDtoList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/find-reservation-by-id/{id}")
    public ResponseEntity<?> findReservationById(@PathVariable Long id) {
        ReservationDto reservation = reservationService.findReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/change-reservation-status/{id}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok().body(reservationService.changeReservationStatus(id, status));
    }


    @DeleteMapping("/delete-reservation-by-id/{userId}/{id}")
    public ResponseEntity<?> deleteReservationById(@PathVariable Long userId, @PathVariable Long id) {
        reservationService.deleteReservationById(userId, id);
        return ResponseEntity.ok(
                StringToJsonDto.builder()
                        .message("Reservation Canceled Successfully")
                        .build()
        );
    }

}
