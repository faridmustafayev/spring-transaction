package com.example.practice.service.abstraction;

import com.example.practice.model.criteria.InvoiceCriteria;
import com.example.practice.model.criteria.PageCriteria;
import com.example.practice.model.request.InvoiceRequest;
import com.example.practice.model.response.InvoiceResponse;
import com.example.practice.model.response.PageableResponse;

public interface InvoiceService {
    void saveInvoice(InvoiceRequest invoice) /*throws Exception*/;

    InvoiceResponse getInvoice(Long id);

    void updateInvoice(Long id, InvoiceRequest invoiceRequest);

    void deleteInvoice(Long id);

    PageableResponse<InvoiceResponse> getInvoices(PageCriteria pageCriteria,
                                                  InvoiceCriteria invoiceCriteria);
}
