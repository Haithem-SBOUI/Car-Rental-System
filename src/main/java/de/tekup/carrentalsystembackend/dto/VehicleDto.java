package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.CarBrand;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VehicleDto {

    private String licensePlateNumber;

    private CarBrand brand;

    private String model;

    private int launchYear;

    private String color;

    private long mileage;

    private String fuel; //Gasoline (Petrol), Diesel, Electric

    private String transmissionType;

    private int hp; //horsePower | nbdalouha b gadech men cylindre wala ...

    private int pricePerDay;

    private LocalDate lastMaintenanceDate;

    private Boolean isAvailable;

}
