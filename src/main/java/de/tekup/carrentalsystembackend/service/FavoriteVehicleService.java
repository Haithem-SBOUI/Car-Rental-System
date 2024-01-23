package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.model.FavoriteVehicle;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import de.tekup.carrentalsystembackend.repository.FavoriteVehicleRepository;
import de.tekup.carrentalsystembackend.repository.ReservationRepository;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import de.tekup.carrentalsystembackend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteVehicleService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final FavoriteVehicleRepository favoriteVehicleRepository;

    public User addVehicleToFavorite(Long userId, Long vehicleId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        Optional<FavoriteVehicle> favoriteVehicle = favoriteVehicleRepository.findByUser(user.get());
        FavoriteVehicle fv = new FavoriteVehicle();

        if (favoriteVehicle.isPresent()) {
            fv = favoriteVehicle.get();
            fv.setUser(user.get());
            fv.getVehicles().add(vehicle.get());
        } else {
            fv.setUser(user.get());
            fv.getVehicles().add(vehicle.get());
        }


        favoriteVehicleRepository.save(fv);
        user.get().setFavoriteVehicle(fv);

        return userRepository.save(user.get());
    }

    public FavoriteVehicle findFavoriteByUserId(Long userId) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//             ;
//            return FavoriteVehicleByUserDto.builder()
//                    .userId(userId)
//                    .vehicles(user.get().getFavoriteVehicle().getVehicles())
//                    .build();
//        } else {
//            return null;
//        }

        Optional<User> user = userRepository.findById(userId);
        Optional<FavoriteVehicle> favoriteVehicle = favoriteVehicleRepository.findByUser(user.get());
        if (favoriteVehicle.isPresent()){
            return favoriteVehicle.get();
        } else {
            return null;
        }
    }

    public User removeVehicleFromFavorite(Long userId, Long vehicleId) {
        Optional<User> user = userRepository.findById(userId);
//        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);


        LinkedHashSet<Vehicle> vehicles = new LinkedHashSet<>(user.get().getFavoriteVehicle().getVehicles());
        vehicles.removeIf( vehicle -> vehicle.getId().equals(vehicleId) );

        user.get().getFavoriteVehicle().setVehicles(vehicles);

        return userRepository.save(user.get());
    }
}
