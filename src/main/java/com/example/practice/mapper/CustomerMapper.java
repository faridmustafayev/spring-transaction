package com.example.practice.mapper;

import com.example.practice.dao.entity.CustomerEntity;
import com.example.practice.model.request.CustomerRequest;
import com.example.practice.model.response.CustomerResponse;
import com.example.practice.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import static com.example.practice.model.enums.Status.ACTIVE;
import static com.example.practice.model.enums.Status.IN_PROGRESS;

public enum CustomerMapper {
    CUSTOMER_MAPPER;

    public CustomerEntity buildCustomerEntity(CustomerRequest customer) {
        return CustomerEntity.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .age(customer.getAge())
                .discount(customer.getDiscount())
                .status(ACTIVE)
                .build();
    }

    public CustomerResponse buildCustomerResponse(CustomerEntity customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .age(customer.getAge())
                .status(customer.getStatus())
                .discount(customer.getDiscount())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    public PageableResponse<CustomerResponse> mapPageableCustomerResponse(Page<CustomerEntity> customerEntityPage) {
        var customerResponse = customerEntityPage.getContent()
                .stream()
                .map(CUSTOMER_MAPPER::buildCustomerResponse)
                .toList();

        return PageableResponse.<CustomerResponse>builder()
                .content(customerResponse)
                .hasNextPage(customerEntityPage.hasNext())
                .lastPageNumber(customerEntityPage.getTotalPages())
                .totalElements(customerEntityPage.getTotalElements())
                .build();
    }

    public void updateCustomerName(CustomerEntity customer, String name) {
        customer.setStatus(IN_PROGRESS);
        customer.setName(name);
    }

}
