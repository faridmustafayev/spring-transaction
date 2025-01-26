package com.example.practice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private Integer age;
    private String email;
    private BigDecimal discount;
    private InvoiceRequest invoiceRequest;
}
