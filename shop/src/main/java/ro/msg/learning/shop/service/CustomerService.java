package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.mapper.CustomerMapper;
import ro.msg.learning.shop.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
    }
    public CustomerDto createCustomer(CustomerDto customerDto) {
        customerRepository.save(customerMapper.toEntity(customerDto));
        return customerDto;
    }
}
