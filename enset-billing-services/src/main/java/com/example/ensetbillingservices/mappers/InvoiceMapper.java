package com.example.ensetbillingservices.mappers;

import com.example.ensetbillingservices.dto.InvoiceRequestDTO;
import com.example.ensetbillingservices.dto.InvoiceResponseDTO;
import com.example.ensetbillingservices.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
