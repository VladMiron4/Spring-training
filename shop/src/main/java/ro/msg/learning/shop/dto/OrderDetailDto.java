package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.key.OrderDetailId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    OrderDetailId orderDetailId;
    private Integer quantity;
    private Product product;
    private Order order;
}
