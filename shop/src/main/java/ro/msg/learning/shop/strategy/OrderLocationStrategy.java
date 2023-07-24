package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.NegativeQuantityException;
import ro.msg.learning.shop.exception.ProductNotFoundException;

public interface OrderLocationStrategy {
    public OrderDto create(CreateOrderDto createOrderDto) throws NegativeQuantityException, ProductNotFoundException, LocationNotFoundException;
}
