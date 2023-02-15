package com.epam.jpop.customer.rest;

import com.epam.jpop.customer.api.CustomersApi;
import com.epam.jpop.customer.model.AddressDTO;
import com.epam.jpop.customer.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AddressController implements CustomersApi {

    private AddressService addressService;

    @Override
    public ResponseEntity<AddressDTO> addCustomerAddress(Long customerId, AddressDTO addressDTO) {
        AddressDTO savedAddressDTO = addressService.addCustomerAddress(customerId, addressDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{addressId}").buildAndExpand(savedAddressDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(savedAddressDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCustomerAddress(Long customerId, Long addressId) {
        return CustomersApi.super.deleteCustomerAddress(customerId, addressId);
    }

    @Override
    public ResponseEntity<AddressDTO> getCustomerHomeAddress(Long customerId) {
        return CustomersApi.super.getCustomerHomeAddress(customerId);
    }

    @Override
    public ResponseEntity<AddressDTO> updateCustomerAddress(Long customerId, Long addressId, AddressDTO addressDTO) {
        return CustomersApi.super.updateCustomerAddress(customerId, addressId, addressDTO);
    }
}
