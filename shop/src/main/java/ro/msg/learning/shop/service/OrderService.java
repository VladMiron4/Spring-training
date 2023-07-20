package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.OrderDto;

public interface OrderService {
    public OrderDto createOrder(OrderDto orderDto);
}
