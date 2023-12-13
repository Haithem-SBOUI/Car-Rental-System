package de.tekup.carrentalsystembackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idVehicle;

    private String model;

    private String licensePlateNumber;

    private int pricePerDay;

    private String fuel;

    private Boolean isAvailable;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Assurance assurance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
