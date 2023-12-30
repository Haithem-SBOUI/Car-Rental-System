package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.dto.RegisterDto;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(RegisterDto registerDto) {
        // Check if email is already in use
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        // Check if username is already in use
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exist");
        }

        User user = convertDtoToInstance(registerDto);
        return userRepository.save(user);


    }

    private User convertDtoToInstance(RegisterDto registerDto) {

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setFirstname(registerDto.getFirstname());
        user.setLastname(registerDto.getLastname());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
//        user.setImageUrl(registerDto.getImageUrl());
        user.setImageUrl("https://www.freeiconspng.com/uploads/profile-icon-28.png");
        user.setPassword(registerDto.getPassword());
        user.setRole(registerDto.getRole());
        return user;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
