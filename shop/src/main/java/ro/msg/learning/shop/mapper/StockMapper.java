package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.dto.StockDto;

@Component
public class StockMapper {

    public Stock toEntity(StockDto stockDto){
        return Stock.builder()
                .Id(stockDto.getStockId())
                .quantity(stockDto.getQuantity())
                .location(stockDto.getLocation())
                .product(stockDto.getProduct())
                .build();
    }
}
