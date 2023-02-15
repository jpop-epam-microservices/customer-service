package com.epam.jpop.customer.service;

import com.epam.jpop.customer.mapper.AddressMapper;
import com.epam.jpop.customer.model.AddressDTO;
import com.epam.jpop.customer.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    private AddressMapper addressMapper;

    public AddressDTO addCustomerAddress(Long customerId, AddressDTO addressDTO) {
        return addressDTO;
    }
}
