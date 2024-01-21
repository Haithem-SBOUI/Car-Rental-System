package de.tekup.carrentalsystembackend.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Builder
public class ReservationCreationRequestDto {

    private Long userId;

    private Long vehicleId;

    private LocalDateTime pickupDate;

    private LocalDateTime returnDate;

    private float totalCost;

    private String status;

}
