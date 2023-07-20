package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.OrderDto;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order){
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .addressCity(order.getAddressCity())
                .adressCounty(order.getAddressCounty())
                .adressStreet(order.getAddressStreet())
                .customerId(order.getCustomerId())
                .addressCountry(order.getAddressCountry())
                .date(order.getDate())
                .build();
    }
    public Order toEntity(OrderDto orderDto){
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .addressCity(orderDto.getAddressCity())
                .addressCounty(orderDto.getAdressCounty())
                .addressStreet(orderDto.getAdressStreet())
                .customerId(orderDto.getCustomerId())
                .addressCountry(orderDto.getAddressCountry())
                .date(orderDto.getDate())
                .build();
    }

}
