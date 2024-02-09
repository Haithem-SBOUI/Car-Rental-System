package de.tekup.carrentalsystembackend.dto.modelMapper;

import de.tekup.carrentalsystembackend.dto.InvoiceDto;
import de.tekup.carrentalsystembackend.model.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class InvoiceMapper {
    private final ModelMapper modelMapper;

    public InvoiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InvoiceDto toDTO(Invoice invoice) {
        return modelMapper.map(invoice, InvoiceDto.class);
    }

    public Invoice toEntity(InvoiceDto invoiceDto) {
        return modelMapper.map(invoiceDto, Invoice.class);
    }

    public List<InvoiceDto> toDtoList(List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(this::toDTO)
                .collect(toList());
    }

}
