package com.epam.jpop.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.epam.jpop.customer.domain.Customer;
import com.epam.jpop.customer.exception.CustomerNotFoundException;
import com.epam.jpop.customer.mapper.CustomerMapper;
import com.epam.jpop.customer.model.CustomerDTO;
import com.epam.jpop.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldGetAllCustomers() {
        // Given
        List<Customer> customers = List.of(new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> expectedCustomerDTOs = List.of(new CustomerDTO(), new CustomerDTO());
        when(customerMapper.fromCustomerList(customers)).thenReturn(expectedCustomerDTOs);

        // When
        List<CustomerDTO> actualCustomerDTOs = customerService.getAllCustomers();

        // Then
        assertEquals(expectedCustomerDTOs, actualCustomerDTOs);
    }

    @Test
    void shouldCreateCustomer() {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = new Customer();
        customer.setAddresses(Collections.emptyList());
        when(customerMapper.toCustomer(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        CustomerDTO expectedCustomerDTO = new CustomerDTO();
        when(customerMapper.fromCustomer(customer)).thenReturn(expectedCustomerDTO);

        // When
        CustomerDTO actualCustomerDTO = customerService.createCustomer(customerDTO);

        // Then
        assertEquals(expectedCustomerDTO, actualCustomerDTO);
    }

    @Test
    void shouldUpdateCustomer() {
        // Given
        Long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setAddresses(Collections.emptyList());
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toCustomer(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        CustomerDTO expectedCustomerDTO = new CustomerDTO();
        when(customerMapper.fromCustomer(customer)).thenReturn(expectedCustomerDTO);

        // When
        CustomerDTO actualCustomerDTO = customerService.updateCustomer(customerId, customerDTO);

        // Then
        assertEquals(expectedCustomerDTO, actualCustomerDTO);
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionOnUpdateCustomer() {
        // Given
        Long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customerId, customerDTO));
    }

    @Test
    void shouldDeleteCustomer() {
        // Given
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // When
        customerService.deleteCustomer(customerId);

        // Then
        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionOnDeleteCustomer() {
        // Given
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(customerId));
    }
}
