package de.tekup.carrentalsystembackend.model;


import de.tekup.carrentalsystembackend.model.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime issuedDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @OneToOne
    @JoinColumn(name = "reservation_id", unique = true)
    private Reservation reservation;


}
