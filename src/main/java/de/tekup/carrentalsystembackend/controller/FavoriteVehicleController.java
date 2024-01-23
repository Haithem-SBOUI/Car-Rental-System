package de.tekup.carrentalsystembackend.controller;


import de.tekup.carrentalsystembackend.model.FavoriteVehicle;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.repository.FavoriteVehicleRepository;
import de.tekup.carrentalsystembackend.service.FavoriteVehicleService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fa")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class FavoriteVehicleController {
    private final FavoriteVehicleService favoriteVehicleService;

    @PostMapping("add-vehicle-to-favorite/{userId}/{vehicleId}")
    public ResponseEntity<?> addVehicleToFavorite(@PathVariable Long userId, @PathVariable Long vehicleId) {
        User favoriteVehicle =  favoriteVehicleService.addVehicleToFavorite(userId, vehicleId);
         return ResponseEntity.ok(favoriteVehicle);
    }

    @GetMapping("find-favorite-by-userId/{userId}")
    public ResponseEntity<?> findFavoriteByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteVehicleService.findFavoriteByUserId(userId));
    }


    @DeleteMapping("remove-vehicle-from-favorite/{userId}/{vehicleId}")
    public ResponseEntity<?> removeVehicleFromFavorite( @PathVariable Long userId, @PathVariable Long vehicleId ){
        User favoriteVehicle =  favoriteVehicleService.removeVehicleFromFavorite(userId, vehicleId);
        return ResponseEntity.ok(favoriteVehicle);
    }

}
