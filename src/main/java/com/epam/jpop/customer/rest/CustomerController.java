package com.epam.jpop.customer.rest;

import com.epam.jpop.customer.api.CustomersApi;
import com.epam.jpop.customer.model.AddressDTO;
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
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @Override
    public ResponseEntity<CustomerDTO> updateCustomer(Long customerId, CustomerDTO customer) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(updatedCustomer.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(updatedCustomer);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AddressDTO> addCustomerAddress(Long customerId, AddressDTO addressDTO) {
        return CustomersApi.super.addCustomerAddress(customerId, addressDTO);
    }

    @Override
    public ResponseEntity<AddressDTO> getCustomerHomeAddress(Long customerId) {
        return CustomersApi.super.getCustomerHomeAddress(customerId);
    }

    @Override
    public ResponseEntity<AddressDTO> updateCustomerAddress(Long customerId, Long addressId, AddressDTO addressDTO) {
        return CustomersApi.super.updateCustomerAddress(customerId, addressId, addressDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCustomerAddress(Long customerId, Long addressId) {
        return CustomersApi.super.deleteCustomerAddress(customerId, addressId);
    }
}
