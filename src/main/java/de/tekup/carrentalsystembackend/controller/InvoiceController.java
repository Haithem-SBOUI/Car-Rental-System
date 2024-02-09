package de.tekup.carrentalsystembackend.controller;


import de.tekup.carrentalsystembackend.dto.InvoiceDto;
import de.tekup.carrentalsystembackend.dto.modelMapper.InvoiceMapper;
import de.tekup.carrentalsystembackend.model.Invoice;
import de.tekup.carrentalsystembackend.repository.InvoiceRepository;
import de.tekup.carrentalsystembackend.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;


    @GetMapping("/find-all-invoice")
    public ResponseEntity<?> findAllInvoices() {
        List<InvoiceDto> allInvoices = invoiceService.findAllInvoices();
        return ResponseEntity.ok(allInvoices);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMeeting(@RequestBody @Valid InvoiceDto invoiceDto) {
        InvoiceDto createdInvoice = invoiceService.createInvoice(invoiceDto);
        return ResponseEntity.ok(createdInvoice);
    }

    @GetMapping("/find-invoice-by-id/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        InvoiceDto invoices = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/find-invoice-by-reservation-id/{reservationId}")
    public ResponseEntity<?> getInvoiceByReservationId(@PathVariable Long reservationId) {
        InvoiceDto invoices = invoiceService.getInvoiceByReservationId(reservationId);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/find-all-invoice-by-user-id/{userId}")
    public ResponseEntity<?> findAllInvoicesByUserId(@PathVariable Long userId) {
        List<InvoiceDto> allInvoices = invoiceService.findAllInvoicesByUserId(userId);
        return ResponseEntity.ok(allInvoices);
    }

    @PutMapping("/update-invoice/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDto newInvoiceDto) {
        InvoiceDto invoiceDto = invoiceService.updateInvoice(id, newInvoiceDto);
        return ResponseEntity.ok().body(invoiceDto);
    }

    @DeleteMapping("/delete-invoice-by-id/{id}")
    public ResponseEntity<?> deleteInvoiceById(@PathVariable Long id) {
        invoiceService.deleteInvoiceById(id);
        return ResponseEntity.noContent().build();
    }



}
