package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;

import java.util.UUID;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId().toString())
                .addressCity(order.getAddressCity())
                .addressCounty(order.getAddressCounty())
                .addressStreet(order.getAddressStreet())
                .customerId(order.getCustomerId().toString())
                .addressCountry(order.getAddressCountry())
                .date(order.getDate())
                .build();
    }

    public Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .addressCity(orderDto.getAddressCity())
                .addressCounty(orderDto.getAddressCounty())
                .addressStreet(orderDto.getAddressStreet())
                .customerId(UUID.fromString(orderDto.getCustomerId()))
                .addressCountry(orderDto.getAddressCountry())
                .date(orderDto.getDate())
                .build();
    }
    public OrderDto mapCreateOrderToDto(CreateOrderDto createOrderDto){
        return OrderDto.builder()
                .addressCounty(createOrderDto.getAddressCounty())
                .addressCountry(createOrderDto.getAddressCountry())
                .addressStreet(createOrderDto.getAddressStreet())
                .addressCounty(createOrderDto.getAddressCounty())
                .addressCity(createOrderDto.getAddressCity())
                .customerId(createOrderDto.getCustomerId())
                .build();
    }

}
