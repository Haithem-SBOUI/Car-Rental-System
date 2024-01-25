package de.tekup.carrentalsystembackend.dto.mapper;

import de.tekup.carrentalsystembackend.dto.UserDto;
import de.tekup.carrentalsystembackend.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto toDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}