package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.dto.AuthRequestDto;
import de.tekup.carrentalsystembackend.dto.AuthResponseDto;
import de.tekup.carrentalsystembackend.dto.RegisterDto;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.enums.UserRole;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<?> login(AuthRequestDto authRequest) {
        Optional<User> user = userRepository.findByEmail(authRequest.getEmail());
        if (user.isPresent()) {
            if (authRequest.getPassword().equals(user.get().getPassword())) {
                Long id = user.get().getId();
                String email = user.get().getEmail();
                userRepository.save(user.get());
                return ResponseEntity.ok(AuthResponseDto.builder()
                        .id(id)
                        .email(email)
                        .build());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @Transactional
    public User register(RegisterDto newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = convertDtoToUserEntity(newUser);
        user.setRole(UserRole.ROLE_ADMIN);
        return userRepository.save(user);
    }

    private User convertDtoToUserEntity(RegisterDto registerDto) {

        User user = new User();
        user.setFirstname(registerDto.getFirstname());
        user.setLastname(registerDto.getLastname());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setPassword(registerDto.getPassword());
        user.setRole(registerDto.getRole());
        return user;
    }


    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
