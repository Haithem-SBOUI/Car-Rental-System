package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.dto.UserDto;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.Reservation;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Reservation}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto implements Serializable {
    Long id;
    @NotNull
    LocalDate pickupDate;
    @NotNull
    LocalDate returnDate;
    float totalPrice;
    int nbDate;
    String status;
    @NotNull
    UserDto user;
    @NotNull
    VehicleDto vehicle;
}