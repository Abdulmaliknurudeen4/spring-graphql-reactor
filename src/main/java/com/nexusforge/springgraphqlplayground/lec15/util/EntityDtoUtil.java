package com.nexusforge.springgraphqlplayground.lec15.util;

import com.nexusforge.springgraphqlplayground.lec15.dto.CustomerDto;
import com.nexusforge.springgraphqlplayground.lec15.entity.Customer;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    public static Customer toEntity(Integer id, CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        customer.setId(id);
        return customer;
    }

    public static CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }
}
