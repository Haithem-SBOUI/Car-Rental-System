package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.UserRole;
import lombok.Data;

@Data
public class RegisterDto {

    private String username;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    private String imageUrl;

    private UserRole role;

}
