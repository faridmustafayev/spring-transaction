package com.example.practice.controller;

import com.example.practice.model.criteria.CustomerCriteria;
import com.example.practice.model.criteria.PageCriteria;
import com.example.practice.model.request.CustomerRequest;
import com.example.practice.model.response.CustomerResponse;
import com.example.practice.model.response.PageableResponse;
import com.example.practice.service.abstraction.CustomerService;
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
@RequestMapping("v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    /*@PostMapping
    @ResponseStatus(CREATED)
    public void saveCustomer(@RequestBody CustomerRequest customer) *//*throws Exception*//*{
        customerService.saveCustomer(customer);
    }*/

    @PostMapping
    @ResponseStatus(CREATED)
    public void applyDiscountAndSaveCustomer(@RequestBody CustomerRequest customer) {
        customerService.applyDiscountAndSaveCustomer(customer);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @PatchMapping("/{id}/name")
    @ResponseStatus(NO_CONTENT)
    public void updateName(@PathVariable Long id, @RequestParam String name){
        customerService.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @GetMapping
    public PageableResponse<CustomerResponse> getCustomers(PageCriteria pageCriteria,
                                                           CustomerCriteria customerCriteria){
        return customerService.getCustomers(pageCriteria, customerCriteria);
    }
}
