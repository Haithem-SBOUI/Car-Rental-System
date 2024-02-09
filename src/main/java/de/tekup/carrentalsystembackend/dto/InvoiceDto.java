package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.dto.ReservationDto;
import de.tekup.carrentalsystembackend.dto.UserDto;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.Invoice;
import de.tekup.carrentalsystembackend.model.enums.PaymentMethodEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Invoice}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto implements Serializable {
    Long id;

    @NotNull
    LocalDateTime issuedDate;

    @NotNull
    PaymentMethodEnum paymentMethod;

    @NotNull
    UserDto admin;

    @NotNull
    ReservationDto reservation;
}