package com.example.practice.service.abstraction;

import com.example.practice.model.criteria.CustomerCriteria;
import com.example.practice.model.criteria.PageCriteria;
import com.example.practice.model.request.CustomerRequest;
import com.example.practice.model.response.CustomerResponse;
import com.example.practice.model.response.PageableResponse;

public interface CustomerService {
    void saveCustomer(CustomerRequest customer) /*throws Exception*/;

    void applyDiscountAndSaveCustomer(CustomerRequest customer);

    CustomerResponse getCustomer(Long id);

    void updateName(Long id, String name);

    void deleteCustomer(Long id);

    PageableResponse<CustomerResponse> getCustomers(PageCriteria pageCriteria,
                                                    CustomerCriteria customerCriteria);
}
