package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.StockDto;

import java.util.UUID;

@Component
public class StockMapper {

    public Stock toEntity(StockDto stockDto) {
        return Stock.builder()
                .Id(StockId.builder().productId(UUID.fromString(stockDto.getProductId())).locationId(UUID.fromString(stockDto.getLocationId())).build())
                .quantity(stockDto.getQuantity())
                .location(stockDto.getLocation())
                .product(stockDto.getProduct())
                .build();
    }
    public StockId mapStockId(UUID locationId,String productId){
        return StockId.builder()
                .locationId(locationId)
                .productId(UUID.fromString(productId))
                .build();
    }
}
