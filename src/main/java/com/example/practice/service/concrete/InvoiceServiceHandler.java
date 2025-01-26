package com.example.practice.service.concrete;

import com.example.practice.aop.annotation.Log;
import com.example.practice.dao.entity.InvoiceEntity;
import com.example.practice.dao.repository.InvoiceRepository;
import com.example.practice.exception.NotFoundException;
import com.example.practice.model.criteria.InvoiceCriteria;
import com.example.practice.model.criteria.PageCriteria;
import com.example.practice.model.request.InvoiceRequest;
import com.example.practice.model.response.InvoiceResponse;
import com.example.practice.model.response.PageableResponse;
import com.example.practice.service.abstraction.InvoiceService;
import com.example.practice.service.specification.InvoiceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.example.practice.mapper.InvoiceMapper.INVOICE_MAPPER;
import static com.example.practice.model.enums.ExceptionConstant.INVOICE_NOT_FOUND;
import static com.example.practice.model.enums.Status.DELETED;

@Log
@Service
@RequiredArgsConstructor
public class InvoiceServiceHandler implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    /*@Override
    public void saveInvoice(InvoiceRequest invoice) {
        var invoiceEntity = INVOICE_MAPPER.buildInvoiceEntity(invoice);
        invoiceRepository.save(invoiceEntity);
    }*/

    @Override
    public void saveInvoice(InvoiceRequest invoice) {
        throw new RuntimeException();
        // The Transactional annotation roll back unchecked exceptions by default.
    }

    /*@Override
    public void saveInvoice(InvoiceRequest invoice) throws Exception {
        throw new Exception();
        //The transactional annotation cannot roll back checked exceptions by default.
        //That is the way use @Transactional(rollbackFor = Exception.class) or try catch(throw RuntimeException(ourself) during catch )
    }*/

    @Override
    public InvoiceResponse getInvoice(Long id) {
        var invoice = fetchInvoiceIfExist(id);
        return INVOICE_MAPPER.buildInvoiceResponse(invoice);
    }

    @Override
    public void updateInvoice(Long id, InvoiceRequest invoiceRequest) {
        var invoice = fetchInvoiceIfExist(id);
        invoice.setAmount(invoiceRequest.getAmount());
        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        InvoiceEntity invoice = fetchInvoiceIfExist(id);
        invoice.setStatus(DELETED);
        invoiceRepository.save(invoice);
    }

    @Override
    public PageableResponse<InvoiceResponse> getInvoices(PageCriteria pageCriteria,
                                                         InvoiceCriteria invoiceCriteria) {
        var invoiceEntityPage = invoiceRepository.findAll(
                InvoiceSpecification.of(invoiceCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").ascending()));

        return INVOICE_MAPPER.mapPageableCustomerAndInvoiceResponse(invoiceEntityPage);
    }

    private InvoiceEntity fetchInvoiceIfExist(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() ->
                new NotFoundException(INVOICE_NOT_FOUND.getCode(), INVOICE_NOT_FOUND.getMessage()));
    }

}
