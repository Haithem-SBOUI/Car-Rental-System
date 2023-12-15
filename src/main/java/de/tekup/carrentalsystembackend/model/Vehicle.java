package de.tekup.carrentalsystembackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "license_plate_number", unique = true)
    private String licensePlateNumber;

    // ?Vehicle Details
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CarBrand brand;

    private String model;

    @Column(name = "launch_year")
    private int launchYear;

    private String color;

    private long mileage;

    // ?Technical Specifications
    private String fuel; //Gasoline (Petrol), Diesel, Electric

    @Column(name = "transmission_type")
    private String transmissionType;

    @Column(name = "hp")
    private int hp; //horsePower | nbdalouha b gadech men cylindre wala ...

    @Column(name = "price_per_day")
    private int pricePerDay;

    private Boolean isAvailable;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Insurance insurance;


    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

}
