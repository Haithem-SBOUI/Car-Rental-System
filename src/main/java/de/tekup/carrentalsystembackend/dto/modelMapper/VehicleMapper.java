package de.tekup.carrentalsystembackend.dto.modelMapper;

import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class VehicleMapper {
    private final ModelMapper modelMapper;

    public VehicleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VehicleDto toDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }
    public Vehicle toEntity(VehicleDto vehicleDto) {
        return modelMapper.map(vehicleDto, Vehicle.class);
    }

    public List<VehicleDto> toDtoList(List<Vehicle> vehicleList) {
            return vehicleList.stream()
                    .map(this::toDTO)
                    .collect(toList());
        }
}
