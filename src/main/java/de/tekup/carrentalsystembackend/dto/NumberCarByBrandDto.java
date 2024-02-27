package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumberCarByBrandDto {
    private CarBrand carBrand;
    private long quantity;
}
