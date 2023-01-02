package com.epam.jpop.customer.exception;

import static java.text.MessageFormat.format;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super(format("Customer with customer id: {0} not found", customerId));
    }

}
