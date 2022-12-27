package com.epam.jpop.customer.service;

import com.epam.jpop.customer.domain.Customer;
import com.epam.jpop.customer.exception.CustomerNotFoundException;
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
        return customerMapper.fromCustomer(getCustomer(customerId));
    }

    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        getCustomer(customerId);
        Customer updatedCustomer = customerMapper.toCustomer(customerDTO);
        updatedCustomer.setId(customerId);
        //set customer
        updatedCustomer.getAddresses().forEach(address -> address.setCustomer(updatedCustomer));
        return customerMapper.fromCustomer(customerRepository.save(updatedCustomer));
    }

    public void deleteCustomer(Long customerId) {
        // find customer else throw exception
        getCustomer(customerId);
        customerRepository.deleteById(customerId);
    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
}
