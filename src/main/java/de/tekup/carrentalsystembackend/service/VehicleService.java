package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.core.exception.type.InternalServerErrorException;
import de.tekup.carrentalsystembackend.core.exception.type.NotFoundException;
import de.tekup.carrentalsystembackend.core.exception.type.UnauthorizedException;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.dto.modelMapper.VehicleMapper;
import de.tekup.carrentalsystembackend.model.*;
import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import de.tekup.carrentalsystembackend.model.enums.FuelType;
import de.tekup.carrentalsystembackend.model.enums.TransmType;
import de.tekup.carrentalsystembackend.model.enums.UserRole;
import de.tekup.carrentalsystembackend.repository.ReservationRepository;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import de.tekup.carrentalsystembackend.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final VehicleMapper vehicleMapper;
    private final FileUploadService fileUploadService;


    public VehicleDto findVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleById(id);

        return optionalVehicle.map(vehicleMapper::toDTO).orElse(null);
    }


    public List<VehicleDto> findByFilters(LocalDate pickupDate, CarBrand brand, String model, Long maxPrice) {
        if (model.isEmpty()) {
            model = null;
        }
        List<Vehicle> vehicleList = vehicleRepository.findByFilters(pickupDate, brand, model, maxPrice);
        return vehicleMapper.toDtoList(vehicleList);

    }

    public void addVehicle(Long idUser, VehicleDto vehicleDto) throws IOException {

        // Retrieve the user from the database
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        //Checking if the user is ROLE_ADMIN
        if (user.getRole().equals(UserRole.ROLE_ADMIN)) {
            // Create new vehicle instance from the dto
            Vehicle vehicle = convertDtoToInstance(vehicleDto);
            vehicle.setUser(user);
            if (vehicleDto.getPictures() != null && !vehicleDto.getPictures().isEmpty()) {
                List<String> picturePaths = new ArrayList<>();
                for (MultipartFile picture : vehicleDto.getPictures()) {
                    String relativeImagePath = fileUploadService.uploadImage(picture, "vehicle");
                    picturePaths.add(relativeImagePath);
                }

                vehicle.setPicturePaths(picturePaths);
            } else {
                vehicle.setPicturePaths(Collections.singletonList("uploads/vehicle_pictures/default_image.png"));
            }
            vehicle.setIsAvailable(true);
            vehicleRepository.save(vehicle);

            // Add the vehicle to the user's list of vehicles
//            user.getVehicles().add(vehicle);
//            userRepository.save(user);
        } else {
            throw new UnauthorizedException("Your not Admin Role");
        }


    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).get();
    }

    public String updateVehicle(VehicleDto updatedVehicleDto) {
        Vehicle existingVehicle = vehicleRepository.findById(updatedVehicleDto.getId())
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
        Vehicle vehicle = vehicleMapper.toEntity(updatedVehicleDto);
        vehicleRepository.save(vehicle);
        return "Vehicle updated successfully";
    }


    private Vehicle convertDtoToInstance(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();

        vehicle.setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setLaunchYear(vehicleDto.getLaunchYear());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setMileage(vehicleDto.getMileage());
        vehicle.setFuel(vehicleDto.getFuel());
        vehicle.setTransmissionType(vehicleDto.getTransmissionType());
        vehicle.setHorsPower(vehicleDto.getHorsPower());
        vehicle.setPricePerDay(vehicleDto.getPricePerDay());
        vehicle.setLastMaintenanceMileage(vehicleDto.getLastMaintenanceMileage());
        vehicle.setIsAvailable(vehicleDto.getIsAvailable());

        return vehicle;
    }


    public List<VehicleDto> getAllVehicle() {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        if (!allVehicles.isEmpty()) {
            return vehicleMapper.toDtoList(allVehicles);
        } else {
            return Collections.emptyList();
        }
    }

    public Optional<List<Vehicle>> findAllByBrand(CarBrand brand) {
        return vehicleRepository.findAllByBrand(brand);
    }

    public Optional<List<Vehicle>> findAllByBrandAndModel(CarBrand brand, String model) {
        return vehicleRepository.findAllByBrandAndModel(brand, model);
    }

    public Optional<List<Vehicle>> findAllByFuel(FuelType fuel) {
        return vehicleRepository.findAllByFuel(fuel);
    }


    public Optional<List<Vehicle>> findAllByTransType(TransmType transmissionType) {
        return vehicleRepository.findAllByTransmissionType(transmissionType);
    }

    public Optional<List<Vehicle>> findAllByHorsPower(int minhorsePower, int maxhorsePower) {

        if (maxhorsePower > minhorsePower && maxhorsePower > 0 && minhorsePower > 0) {
            Optional<List<Vehicle>> vehicles = vehicleRepository.findAllByHorsPowerBetween(minhorsePower, maxhorsePower);
            if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
                return vehicleRepository.findAllByHorsPowerBetween(minhorsePower, maxhorsePower);

            } else {
                throw new IllegalArgumentException("vehicle not found with this power range");
            }
        } else {
            throw new IllegalArgumentException("Invalid Input value of power range");
        }


    }

    public Optional<List<Vehicle>> findAllByPrice(int minpricePerDay, int maxpricePerDay) {
        // min < max    min ,max >0 if true
        // then find the list of vehicle
        // check list is present
        //return list vehicle
        // If list is not present
        // throw  exeption
        // min < max    min ,max >0 if false
        // throw exption data input invalid
        // min < max    min ,max >0 if true

        if (maxpricePerDay > minpricePerDay && maxpricePerDay > 0 && minpricePerDay > 0) {
            // then find the list of vehicle
            Optional<List<Vehicle>> vehicles = vehicleRepository.findAllByPricePerDayBetween(minpricePerDay, maxpricePerDay);
            // check list is present
            if (vehicles.isPresent() && !vehicles.get().isEmpty()) {
                //return list vehicle
                return vehicleRepository.findAllByPricePerDayBetween(minpricePerDay, maxpricePerDay);
            }
            // If list is not present
            else {
                throw new IllegalArgumentException("vehicle not found with this price range");
            }
            // min < max    min ,max >0 if false
        } else {
            // throw exption data input invalid
            throw new IllegalArgumentException("Invalid input values for min and max value ");
        }

    }

    public Optional<List<Vehicle>> findAllByAvailability(boolean isAvailable) {
        return vehicleRepository.findAllByIsAvailable(isAvailable);
    }

    public List<VehicleDto> findAllFreeVehicleByDateTime(String startDateParam) {

//        LocalDateTime startDate = LocalDateTime.parse(startDateParam);

//        LocalDateTime startDate = LocalDateTime.parse(startDateParam + "T23:59:59.999");
        LocalDate startDate = LocalDate.parse(startDateParam);
//        todo: optimize this query!!!
        Optional<List<Vehicle>> availableVehicles = vehicleRepository.findAllByIsAvailable(true);
//        availableVehicles.ifPresent(vehicles -> vehicles.removeIf(
//                vehicle -> reservationRepository.existsByVehicleAndPickupDateLessThanEqualAndReturnDateGreaterThanEqual(vehicle, startDate, startDate))
//        );

        List<VehicleDto> vehicleDtoList = availableVehicles.get().stream()
                .map(vehicleMapper::toDTO)
                .collect(toList());


        return vehicleDtoList;
    }

    public void deleteVehicleById(Long userId, Long id) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UnauthorizedException("Unauthorized, user Not Found"));
        if (user.getRole().equals(UserRole.ROLE_ADMIN)) {
            vehicleRepository.deleteById(id);
        } else {
            throw new UnauthorizedException("Unauthorized");
        }
        if (vehicleRepository.existsById(id)) {
            throw new InternalServerErrorException("problem while delete Vehicle with id : " + id);
        }
    }

    public List<Object[]> findNumberCarByBrand() {
        CarBrand[] carBrands = CarBrand.values();
        List<Object[]> chartData = new ArrayList<>();

        for (CarBrand brand : carBrands) {
            long quantity = vehicleRepository.countByBrand(brand);
            String label = brand.toString(); // You can customize the label as needed

            Object[] dataPoint = {label, quantity};
            chartData.add(dataPoint);
        }

        return chartData;

}
}
