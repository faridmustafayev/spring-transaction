package com.example.practice.mapper;

import com.example.practice.dao.entity.InvoiceEntity;
import com.example.practice.model.request.InvoiceRequest;
import com.example.practice.model.response.InvoiceResponse;
import com.example.practice.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import static com.example.practice.model.enums.Status.ACTIVE;

public enum InvoiceMapper {
    INVOICE_MAPPER;

    public InvoiceEntity buildInvoiceEntity(InvoiceRequest invoice) {
        return InvoiceEntity.builder()
                .amount(invoice.getAmount())
                .status(ACTIVE)
                .build();
    }

    public InvoiceResponse buildInvoiceResponse(InvoiceEntity invoice) {
        return InvoiceResponse.builder()
                .id(invoice.getId())
                .amount(invoice.getAmount())
                .status(invoice.getStatus())
                .createdAt(invoice.getCreatedAt())
                .updatedAt(invoice.getUpdatedAt())
                .build();
    }

    public PageableResponse<InvoiceResponse> mapPageableCustomerAndInvoiceResponse(Page<InvoiceEntity> entityPage) {
        var invoiceResponse = entityPage.stream()
                .map(INVOICE_MAPPER::buildInvoiceResponse)
                .toList();

        return PageableResponse.<InvoiceResponse>builder()
                .content(invoiceResponse)
                .hasNextPage(entityPage.hasNext())
                .lastPageNumber(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .build();
    }

}
