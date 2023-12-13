package de.tekup.carrentalsystembackend.controller;

import de.tekup.carrentalsystembackend.dto.RegisterDto;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
@CrossOrigin("localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            User user = userService.register(registerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping("/find_by_id/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
