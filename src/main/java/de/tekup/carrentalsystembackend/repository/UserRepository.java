package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.enums.UserRole;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByRoleAndId(UserRole role, Long id);

    @Query("SELECT MONTH(u.createdOn), COUNT(u) FROM User u GROUP BY MONTH(u.createdOn)")
    List<Object[]> countByCreatedOn();


}
