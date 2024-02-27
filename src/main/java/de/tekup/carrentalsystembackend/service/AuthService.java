package de.tekup.carrentalsystembackend.service;


import de.tekup.carrentalsystembackend.dto.AuthResponseDto;
import de.tekup.carrentalsystembackend.dto.RegisterRequestDto;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.enums.UserRole;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final FileUploadService fileUploadService;

    public AuthResponseDto register(RegisterRequestDto newUserDto) throws IOException {
        var user = User.builder()
                .firstname(newUserDto.getFirstname())
                .lastname(newUserDto.getLastname())
                .email(newUserDto.getEmail())
                .password(newUserDto.getPassword())
                .role(UserRole.ROLE_USER)
                .build();

        if (newUserDto.getProfileImage() != null && !newUserDto.getProfileImage().isEmpty()) {
            String relativeImagePath = fileUploadService.uploadImage(newUserDto.getProfileImage(), "user");
            user.setProfilePicturePath(relativeImagePath);
        } else {
            user.setProfilePicturePath("uploads/avatar/default_user_avatar.png");
        }
        userRepository.save(user);
        return AuthResponseDto.builder()
                .token("jwtToken")
                .build();
    }


}


