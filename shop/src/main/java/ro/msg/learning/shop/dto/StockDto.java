package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.key.StockId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDto {
    private StockId stockId;
    private Integer quantity;
}
