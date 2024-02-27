package de.tekup.carrentalsystembackend.controller;


import de.tekup.carrentalsystembackend.dto.AuthRequestDto;
import de.tekup.carrentalsystembackend.dto.RegisterRequestDto;
import de.tekup.carrentalsystembackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute RegisterRequestDto newUser) throws IOException {
        return ResponseEntity.ok(authService.register(newUser));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequest) {
//        return ResponseEntity.ok(authService.login(authRequest));
//    }

}
