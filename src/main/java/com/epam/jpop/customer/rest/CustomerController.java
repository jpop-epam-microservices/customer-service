package com.epam.jpop.customer.rest;

import com.epam.jpop.customer.api.CustomersApi;
import com.epam.jpop.customer.model.CustomerDTO;
import com.epam.jpop.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController implements CustomersApi {

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.createCustomer(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedCustomer);
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(Long customerId) {
        return CustomersApi.super.getCustomerById(customerId);
    }

    @Override
    public ResponseEntity<Void> updateCustomer(Long customerId, CustomerDTO customer) {
        return CustomersApi.super.updateCustomer(customerId, customer);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        return CustomersApi.super.deleteCustomer(customerId);
    }
}
