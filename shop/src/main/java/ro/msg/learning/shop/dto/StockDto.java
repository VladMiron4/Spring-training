package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.key.StockId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDto {
    private StockId stockId;
    private Integer quantity;
    private Product product;
    private Location location;
}
