package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.model.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
}
