package ro.msg.learning.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.mapper.CustomerMapper;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        customerRepository.save(customerMapper.toEntity(customerDto));
        return customerDto;
    }
}
