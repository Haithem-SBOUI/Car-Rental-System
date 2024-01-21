package de.tekup.carrentalsystembackend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class ReservationCreationResponseDto {

    private Long userId;

    private Long vehicleId;

    private LocalDateTime pickupDate;

    private LocalDateTime returnDate;

    private float totalCost;

    private String status;

}
