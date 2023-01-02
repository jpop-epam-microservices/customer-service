package com.epam.jpop.customer.mapper;

import com.epam.jpop.customer.domain.Address;
import com.epam.jpop.customer.model.AddressDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Address toAddress(AddressDTO addressDTO);

    @InheritInverseConfiguration
    AddressDTO fromAddress(Address address);

}
