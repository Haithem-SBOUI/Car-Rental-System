package de.tekup.carrentalsystembackend.dto.mapper;

import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapper {

    private final ModelMapper modelMapper;

    public VehicleDto toDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }

}
