package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.enums.CarBrand;

import java.time.LocalDate;

public class FilterVehicleDto {
    private LocalDate pickupDate;
    private CarBrand brand;
    private String model;
    private int pricePerDay;

}
