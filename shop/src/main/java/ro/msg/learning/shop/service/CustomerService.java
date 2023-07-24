package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.mapper.CustomerMapper;
import ro.msg.learning.shop.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(customerMapper.toEntity(customerDto));
        customerDto.setCustomerId(customer.getCustomerId());
        return customerDto;
    }
}
