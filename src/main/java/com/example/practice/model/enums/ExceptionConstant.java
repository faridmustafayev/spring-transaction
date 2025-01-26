package com.example.practice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionConstant {
    HTTP_METHOD_NOT_CORRECT("HTTP_METHOD_NOT_CORRECT", "http method is not correct"),
    INVOICE_NOT_FOUND("INVOICE_NOT_FOUND", "Invoice not found"),
    CUSTOMER_NOT_FOUND("CUSTOMER_NOT_FOUND", "Customer not found"),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred");
    private final String code;
    private final String message;
}
