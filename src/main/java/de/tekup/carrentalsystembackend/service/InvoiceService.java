package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.core.exception.type.InternalServerErrorException;
import de.tekup.carrentalsystembackend.core.exception.type.InvalidStatusException;
import de.tekup.carrentalsystembackend.core.exception.type.NotFoundException;
import de.tekup.carrentalsystembackend.core.exception.type.UnauthorizedException;
import de.tekup.carrentalsystembackend.dto.InvoiceDto;
import de.tekup.carrentalsystembackend.dto.modelMapper.InvoiceMapper;
import de.tekup.carrentalsystembackend.model.Invoice;
import de.tekup.carrentalsystembackend.repository.InvoiceRepository;
import de.tekup.carrentalsystembackend.repository.ReservationRepository;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static de.tekup.carrentalsystembackend.model.enums.ReservationStatusEnum.CONFIRMED;
import static de.tekup.carrentalsystembackend.model.enums.UserRole.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;


    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {
        Long clientId = invoiceDto.getAdmin().getId();
        Long reservationId = invoiceDto.getReservation().getId();
        if (userRepository.existsByRoleAndId(ROLE_ADMIN, clientId)) {
            throw new UnauthorizedException("Unauthorized Action");
        }
        if (reservationRepository.existsByIdAndStatus(reservationId, CONFIRMED)) {
            throw new InvalidStatusException("Status must be Confirmed to be marked as payed");
        }

        Invoice invoice = invoiceMapper.toEntity(invoiceDto);
        return invoiceMapper.toDTO(invoiceRepository.save(invoice));

    }

    public List<InvoiceDto> findAllInvoices() {
        List<InvoiceDto> alInvoices = invoiceMapper.toDtoList(invoiceRepository.findAll());
        return alInvoices;
    }

    public InvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice Not Found"));
        return invoiceMapper.toDTO(invoice);
    }

    public InvoiceDto getInvoiceByReservationId(Long reservationId) {
        Invoice invoice = invoiceRepository.findByReservation_Id(reservationId)
                .orElseThrow(() -> new NotFoundException("Invoice Not Found"));
        return invoiceMapper.toDTO(invoice);
    }

    public List<InvoiceDto> findAllInvoicesByUserId(Long userId) {
        List<Invoice> allInvoice = invoiceRepository.findByReservation_User_Id(userId)
                .orElseThrow(() -> new NotFoundException("No Invoices Found"));
        List<InvoiceDto> alInvoicesDto = invoiceMapper.toDtoList(allInvoice);
        return alInvoicesDto;
    }

    public InvoiceDto updateInvoice(Long id, InvoiceDto newInvoiceDto) {
        if (invoiceRepository.existsById(id)) {
            Invoice invoice = invoiceMapper.toEntity(newInvoiceDto);
            invoice = invoiceRepository.save(invoice);
            return invoiceMapper.toDTO(invoice);
        } else {
            throw new NotFoundException("Invoice Not Found");
        }


    }

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
        if (invoiceRepository.existsById(id)) {
            throw new InternalServerErrorException("problem while delete Invoice with id : " + id);
        }
    }


    //
}
