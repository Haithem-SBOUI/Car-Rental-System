package de.tekup.carrentalsystembackend.controller;

import de.tekup.carrentalsystembackend.dto.ReservationCreationRequestDto;
import de.tekup.carrentalsystembackend.model.Reservation;
import de.tekup.carrentalsystembackend.service.ReservationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/create-reservation")
    public ResponseEntity<?> createReservation(@RequestBody ReservationCreationRequestDto reservationCreationRequestDto) {
        try {
            return ResponseEntity.ok().body(reservationService.createReservation(reservationCreationRequestDto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
