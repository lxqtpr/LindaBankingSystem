package dev.lxqptr.customerservice.model.mapper;

import dev.lxqptr.customerservice.model.dto.CustomerRequest;
import dev.lxqptr.customerservice.model.dto.CustomerResponse;
import dev.lxqptr.customerservice.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerResponse customerResponse);

    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toResponse(Customer customer);

    CustomerRequest toRequest(Customer customer);

    List<CustomerResponse> toResponse(List<Customer> customers);

}
