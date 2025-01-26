package com.example.practice.controller;

import com.example.practice.model.criteria.InvoiceCriteria;
import com.example.practice.model.criteria.PageCriteria;
import com.example.practice.model.request.InvoiceRequest;
import com.example.practice.model.response.InvoiceResponse;
import com.example.practice.model.response.PageableResponse;
import com.example.practice.service.abstraction.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveInvoice(@RequestBody InvoiceRequest request) /*throws Exception */{
        invoiceService.saveInvoice(request);
    }

    @GetMapping("/{id}")
    public InvoiceResponse getInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @PatchMapping("/{id}/amount")
    @ResponseStatus(NO_CONTENT)
    public void setAmount(@PathVariable Long id,
                          @RequestParam InvoiceRequest invoiceRequest) {
        invoiceService.updateInvoice(id, invoiceRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }

    @GetMapping
    public PageableResponse<InvoiceResponse> getInvoices(PageCriteria pageCriteria,
                                                         InvoiceCriteria invoiceCriteria) {
        return invoiceService.getInvoices(pageCriteria, invoiceCriteria);
    }
}
