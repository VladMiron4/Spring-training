package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.NegativeQuantityException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.strategy.OrderLocationStrategy;

@Service
@AllArgsConstructor
public class OrderService {


    private OrderLocationStrategy orderLocationStrategy;
    public OrderDto createSingleLocationOrder(CreateOrderDto createOrderDto) throws LocationNotFoundException, ProductNotFoundException, NegativeQuantityException {
        return orderLocationStrategy.create(createOrderDto);
    }
}
