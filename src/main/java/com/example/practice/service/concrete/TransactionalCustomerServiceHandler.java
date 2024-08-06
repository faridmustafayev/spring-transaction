package com.example.practice.service.concrete;

import com.example.practice.aop.annotation.Log;
import com.example.practice.dao.repository.CustomerRepository;
import com.example.practice.model.request.CustomerRequest;
import com.example.practice.service.abstraction.InvoiceService;
import com.example.practice.service.abstraction.TransactionalCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.practice.mapper.CustomerMapper.CUSTOMER_MAPPER;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Log
@Service
@RequiredArgsConstructor
public class TransactionalCustomerServiceHandler implements TransactionalCustomerService {
    private final CustomerRepository customerRepository;
    private final InvoiceService invoiceService;

    @Transactional(/*propagation = REQUIRES_NEW,*/ isolation = REPEATABLE_READ)
    @Override
    public void saveCustomer(CustomerRequest customer) {
        customerRepository.save(CUSTOMER_MAPPER.buildCustomerEntity(customer));
        invoiceService.saveInvoice(customer.getInvoiceRequest());
    }
}
