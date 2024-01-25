package de.tekup.carrentalsystembackend.dto.modelMapper;

import de.tekup.carrentalsystembackend.dto.UserDto;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VehicleDto toDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }
}
