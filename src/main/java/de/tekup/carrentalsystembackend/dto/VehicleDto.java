package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.CarBrand;
import de.tekup.carrentalsystembackend.model.FuelType;
import de.tekup.carrentalsystembackend.model.TransmType;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VehicleDto {

    private Long id;

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

// todo: add create Dto from Model

//    public record CarDTO(@NonNull String id, String make, String model) {
//
//        public static CarDTO fromModel(Car car) {
//            return new CarDTO(car.id(), car.make(), car.model());
//        }
//    }

}
