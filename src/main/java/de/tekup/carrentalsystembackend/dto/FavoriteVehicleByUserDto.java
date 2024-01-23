package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.Vehicle;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
public class FavoriteVehicleByUserDto {
    private Long userId;
    private Set<Vehicle> vehicles = new LinkedHashSet<>();
}
