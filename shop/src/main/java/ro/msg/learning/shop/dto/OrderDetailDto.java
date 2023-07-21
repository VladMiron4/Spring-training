package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Integer quantity;
    private Product product;
    private Order order;
}
