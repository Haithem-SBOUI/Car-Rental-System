package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import de.tekup.carrentalsystembackend.model.enums.FuelType;
import de.tekup.carrentalsystembackend.model.enums.TransmType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    private List<String> picturePaths;

    private List<MultipartFile> pictures;


}
