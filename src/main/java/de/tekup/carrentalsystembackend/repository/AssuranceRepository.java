package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuranceRepository extends JpaRepository<Insurance, Long> {
}
