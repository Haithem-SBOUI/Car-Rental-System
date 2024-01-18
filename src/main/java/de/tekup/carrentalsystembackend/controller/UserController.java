package de.tekup.carrentalsystembackend.controller;

import de.tekup.carrentalsystembackend.dto.AuthRequestDto;
import de.tekup.carrentalsystembackend.dto.RegisterDto;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequest) {
        return userService.login(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            User user = userService.register(registerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/find_by_id/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

   @GetMapping("/all_users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
