package de.tekup.carrentalsystembackend.dto;

import lombok.Data;

@Data
public class VehicleDto {

    private String model;

    private String licensePlateNumber;

    private int pricePerDay;

    private String fuel;

    private Boolean isAvailable;

}
