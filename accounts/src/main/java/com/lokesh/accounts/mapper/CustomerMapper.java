package com.lokesh.accounts.mapper;

import com.lokesh.accounts.dto.CustomerDto;
import com.lokesh.accounts.entity.Customer;

public class CustomerMapper {
    public static void mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
    }
    public static void mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
    }
}
