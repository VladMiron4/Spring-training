package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.dto.CustomerDto;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer){
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .userName(customer.getUserName())
                .build();
    }
    public Customer toEntity(CustomerDto customerDto){
        return Customer.builder()
                .customerId(customerDto.getCustomerId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .userName(customerDto.getUserName())
                .build();
    }
}
