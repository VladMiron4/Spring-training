package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.mapper.CustomerMapper;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer = customerRepository.save(customerMapper.toEntity(customerDto));
        customerDto.setCustomerId(customer.getCustomerId().toString());
        return customerDto;
    }

    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customers.forEach(customer -> {
            customerDtoList.add(customerMapper.toDto(customer));
        });
        return customerDtoList;
    }
}
