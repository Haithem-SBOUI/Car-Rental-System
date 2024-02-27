package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.enums.UserRole;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByRoleAndId(UserRole role, Long id);

}
