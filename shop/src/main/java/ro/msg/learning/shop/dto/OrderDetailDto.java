package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Integer quantity;
    private Product product;
    private Order order;
}
