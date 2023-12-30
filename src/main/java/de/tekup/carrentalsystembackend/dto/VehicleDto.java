package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.CarBrand;
import de.tekup.carrentalsystembackend.model.FuelType;
import de.tekup.carrentalsystembackend.model.TransmType;
//import de.tekup.carrentalsystembackend.model.TransmissionType;
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

    private FuelType fuel; //Gasoline (Petrol), Diesel, Electric

    private TransmType transmissionType;

    private int horsPower; //horsePower |

    private int pricePerDay;

    private int lastMaintenanceMileage;

    private Boolean isAvailable;

}
