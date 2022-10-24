package com.example.ensetbillingservices.services;

import com.example.ensetbillingservices.dto.InvoiceRequestDTO;
import com.example.ensetbillingservices.dto.InvoiceResponseDTO;
import com.example.ensetbillingservices.entities.Customer;
import com.example.ensetbillingservices.entities.Invoice;
import com.example.ensetbillingservices.exceptions.CustomerNotFoundExecption;
import com.example.ensetbillingservices.mappers.InvoiceMapper;
import com.example.ensetbillingservices.openfeign.CustomerRestClient;
import com.example.ensetbillingservices.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        /*
        Verification de l'integrite referencielle Invoice / Customer
         */
        Customer customer= null;
        try {
            //Chercher le customer Id
            customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }
        catch (Exception e){
            throw new CustomerNotFoundExecption("Customer not found");
        }
        //Ajouter une facture si le customer exist
        Invoice invoice = invoiceMapper.fromInvoiceDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());

        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        //Chercher la base de donnees a partir de la BD du micro-services
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        //Chercher le client a partir d'une autre BD
        //Communiquer avec un autre micro-services
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices= invoiceRepository.findByCustomerId(customerId);
        for (Invoice invoice: invoices){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices
                .stream()
                .map(invoice -> invoiceMapper.fromInvoice(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice: invoices){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices.stream().map(inv -> invoiceMapper.fromInvoice(inv)).collect(Collectors.toList());
    }
}
