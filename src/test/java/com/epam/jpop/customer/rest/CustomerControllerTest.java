package com.epam.jpop.customer.rest;

import com.epam.jpop.customer.model.CustomerDTO;
import com.epam.jpop.customer.service.CustomerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private CustomerDTO customerDTO;

    @BeforeEach
    public void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstname("John");
        customerDTO.setLastname("Doe");
        customerDTO.setEmail("john.doe@example.com");
    }

    @Test
    void shouldReturnAllCustomers() {
        // given
        List<CustomerDTO> customers = Arrays.asList(
                new CustomerDTO().firstname("John").lastname("Doe"),
                new CustomerDTO().firstname("Jane").lastname("Doe")
        );
        when(customerService.getAllCustomers()).thenReturn(customers);

        // when
        ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(customers);
    }

    @Test
    void shouldCreateCustomer() {
        // given
        CustomerDTO customer = new CustomerDTO().firstname("John").lastname("Doe").id(123L);
        when(customerService.createCustomer(customer)).thenReturn(customer);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/customers");
        request.setContextPath("/customers");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        // when
        ResponseEntity<CustomerDTO> response = customerController.createCustomer(customer);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(customer);
//        assertThat(response.getHeaders().getLocation()).isEqualTo(URI.create("/customers/123"));
    }

    @Test
    public void shouldGetCustomerById() {
        // given
        when(customerService.getCustomerById(1L)).thenReturn(customerDTO);

        // when
        ResponseEntity<CustomerDTO> response = customerController.getCustomerById(1L);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerDTO, response.getBody());
    }

    @Test
    public void shouldUpdateCustomer() {
        // given
        when(customerService.updateCustomer(1L, customerDTO)).thenReturn(customerDTO);

        // when
        ResponseEntity<CustomerDTO> response = customerController.updateCustomer(1L, customerDTO);

        // then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerDTO, response.getBody());
    }

    @Test
    public void shouldDeleteCustomer() {
        // when
        ResponseEntity<Void> response = customerController.deleteCustomer(1L);

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
