package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.strategy.OrderLocationStrategy;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderLocationStrategy orderLocationStrategy;

    public OrderDto create(CreateOrderDto createOrderDto) {
        return orderLocationStrategy.create(createOrderDto);
    }
}
