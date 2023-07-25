package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.BadRequestException;

import java.util.List;

public interface OrderLocationStrategy {
    List<Location> getLocation(CreateOrderDto createOrderDto) throws BadRequestException;
}
