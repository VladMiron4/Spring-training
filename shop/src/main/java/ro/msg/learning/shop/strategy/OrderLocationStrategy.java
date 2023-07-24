package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.BadRequestException;

public interface OrderLocationStrategy {
    public OrderDto create(CreateOrderDto createOrderDto) throws BadRequestException;
}
