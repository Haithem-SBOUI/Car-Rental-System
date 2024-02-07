package de.tekup.carrentalsystembackend.dto.modelMapper;

import de.tekup.carrentalsystembackend.dto.ReservationDto;
import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.Reservation;
import de.tekup.carrentalsystembackend.model.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;

    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReservationDto toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    public Reservation toEntity(ReservationDto ReservationDto) {
        return modelMapper.map(ReservationDto, Reservation.class);
    }

    public List<ReservationDto> toDtoList(List<Reservation> meetings) {
        return meetings.stream()
                .map(this::toDto)
                .collect(toList());
    }
}
