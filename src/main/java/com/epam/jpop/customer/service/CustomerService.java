package com.epam.jpop.customer.service;

import com.epam.jpop.customer.domain.Customer;
import com.epam.jpop.customer.mapper.CustomerMapper;
import com.epam.jpop.customer.model.CustomerDTO;
import com.epam.jpop.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.fromCustomerList(customerRepository.findAll());
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toCustomer(customerDTO);
        //set customer
        customer.getAddresses().forEach(address -> address.setCustomer(customer));
        return customerMapper.fromCustomer(customerRepository.save(customer));
    }

    public CustomerDTO getCustomerById(Long customerId) {

        return null;
    }

    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customer) {
        return null;
    }

    public void deleteCustomer(Long customerId) {
    }
}
