package de.tekup.carrentalsystembackend.repository;

import de.tekup.carrentalsystembackend.dto.InvoiceDto;
import de.tekup.carrentalsystembackend.model.Invoice;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByReservation_Id(Long id);

    Optional<List<Invoice>> findByReservation_User_Id(Long userId);

}
