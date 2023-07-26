package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.key.OrderDetailId;

import java.util.UUID;

@Component
public class OrderDetailMapper {

    public OrderDetailId mapOrderDetailId (String productId, UUID orderId){
        return  OrderDetailId.builder().
        productId(UUID.fromString(productId)).orderId(orderId).build();
    }
    public OrderDetail mapOrderDetail(OrderDetailId orderDetailId, Integer quantity, UUID locationId, Order order, Product product){
        return OrderDetail.builder()
                .orderDetailId(orderDetailId)
                .product(product)
                .order(order)
                .quantity(quantity)
                .locationId(locationId)
                .build();
    }

}
