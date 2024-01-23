package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.FavoriteVehicle;
import de.tekup.carrentalsystembackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteVehicleRepository extends JpaRepository<FavoriteVehicle, Long> {

    Optional<FavoriteVehicle> findByUser(User user);


}
