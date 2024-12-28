package com.example.practice.service.concrete;

import com.example.practice.aop.annotation.Log;
import com.example.practice.dao.entity.CustomerEntity;
import com.example.practice.dao.repository.CustomerRepository;
import com.example.practice.exception.NotFoundException;
import com.example.practice.model.criteria.CustomerCriteria;
import com.example.practice.model.criteria.PageCriteria;
import com.example.practice.model.request.CustomerRequest;
import com.example.practice.model.response.CustomerResponse;
import com.example.practice.model.response.PageableResponse;
import com.example.practice.service.abstraction.CustomerService;
import com.example.practice.service.abstraction.InvoiceService;
import com.example.practice.service.abstraction.TransactionalCustomerService;
import com.example.practice.service.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.example.practice.mapper.CustomerMapper.CUSTOMER_MAPPER;
import static com.example.practice.model.enums.ExceptionConstant.CUSTOMER_NOT_FOUND;
import static com.example.practice.model.enums.Status.DELETED;

@Log
@Service
@RequiredArgsConstructor
public class CustomerServiceHandler implements CustomerService {
    private final CustomerRepository customerRepository;
    private final InvoiceService invoiceService;
    private final TransactionalCustomerService transactionalCustomerService;
    private CustomerService customerService;

    /*@Transactional*//*(rollbackFor = Exception.class)*//* // It should be from springframework, not from jakarta
    @Override
    public void saveCustomer(CustomerRequest customer) *//*throws Exception*//* {
        var customerEntity = CUSTOMER_MAPPER.buildCustomerEntity(customer);
        customerRepository.save(customerEntity);
        invoiceService.saveInvoice(customer.getInvoiceRequest());
    }*/

    @Lazy    // with self injection
    public void setCustomerServiceHandler(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void applyDiscountAndSaveCustomer(CustomerRequest customer) {
        BigDecimal discount = calculateDiscount(customer.getInvoiceRequest().getAmount());
        customer.setDiscount(discount);
        /*saveCustomer(customer);*/
//        transactionalCustomerService.saveCustomer(customer);  // separate Bean
        customerService.saveCustomer(customer);
    }

    @Transactional
    @Override
    public void saveCustomer(CustomerRequest customer) {
        var customerEntity = CUSTOMER_MAPPER.buildCustomerEntity(customer);
        customerRepository.save(customerEntity);
        invoiceService.saveInvoice(customer.getInvoiceRequest());
    }

    @Override
    public CustomerResponse getCustomer(Long id) {
        var customer = fetchCustomerIfExist(id);
        return CUSTOMER_MAPPER.buildCustomerResponse(customer);
    }

    @Override
    public void updateName(Long id, String name) {
        var customer = fetchCustomerIfExist(id);
        CUSTOMER_MAPPER.updateCustomerName(customer, name);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        var customer = fetchCustomerIfExist(id);
        customer.setStatus(DELETED);
        customerRepository.save(customer);
    }

    @Override
    public PageableResponse<CustomerResponse> getCustomers(PageCriteria pageCriteria,
                                                           CustomerCriteria customerCriteria) {
        var customerEntityPage = customerRepository.findAll(
                CustomerSpecification.of(customerCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending()));

        return CUSTOMER_MAPPER.mapPageableCustomerResponse(customerEntityPage);
    }

    private CustomerEntity fetchCustomerIfExist(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new NotFoundException(CUSTOMER_NOT_FOUND.getCode(), CUSTOMER_NOT_FOUND.getMessage()));
    }

    private BigDecimal calculateDiscount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(5000)) >= 0) {
            return amount.multiply(BigDecimal.valueOf(0.20));
        } else if (amount.compareTo(BigDecimal.valueOf(1000)) >= 0) {
            return amount.multiply(BigDecimal.valueOf(0.10));
        } else {
            return BigDecimal.ZERO;
        }
    }

}
