package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.dto.modelMapper.VehicleMapper;
import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import de.tekup.carrentalsystembackend.repository.ReservationRepository;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import de.tekup.carrentalsystembackend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AnalysisService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final VehicleMapper vehicleMapper;
    private final FileUploadService fileUploadService;

    public List<Object[]> findNumberCarByBrand() {
        CarBrand[] carBrands = CarBrand.values();
        List<Object[]> chartData = new ArrayList<>();

        for (CarBrand brand : carBrands) {
            long quantity = vehicleRepository.countByBrand(brand);
            String label = brand.toString();

            Object[] dataPoint = {label, quantity};
            chartData.add(dataPoint);
        }

        return chartData;
    }

    public List<Object[]> countByPickupMonth() {
        return reservationRepository.countByPickupMonth();
    }
    public List<Object[]> countByCreatedOn() {
        return userRepository.countByCreatedOn();
    }
}
