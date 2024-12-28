package com.example.practice.service.abstraction;

import com.example.practice.model.request.CustomerRequest;

public interface TransactionalCustomerService {
    void saveCustomer(CustomerRequest customer);
}
