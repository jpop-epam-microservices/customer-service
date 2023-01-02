package com.epam.jpop.customer.mapper;

import com.epam.jpop.customer.domain.Customer;
import com.epam.jpop.customer.model.CustomerDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toCustomer(CustomerDTO customerDTO);

    @InheritInverseConfiguration
    CustomerDTO fromCustomer(Customer customer);

    List<Customer> toCustomerList(List<CustomerDTO> customerDTOList);

    @InheritInverseConfiguration
    List<CustomerDTO> fromCustomerList(List<Customer> customerList);
}
