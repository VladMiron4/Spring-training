package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.domain.key.OrderDetailId;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderProductDto;
import ro.msg.learning.shop.exception.BadRequestException;
import ro.msg.learning.shop.mapper.OrderDetailMapper;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ro.msg.learning.shop.message.Messages.*;

@AllArgsConstructor
public class MostAbundantLocation implements OrderLocationStrategy {

    private final StockRepository stockRepository;
    private final LocationRepository locationRepository;


    private Location getMaximumStockLocation(List<UUID> orderedProductLocations, OrderProductDto orderProductDto) {
        Stock maximumStock = Stock.builder().quantity(0).build();
        if (orderedProductLocations.size()==0){
            return Location.builder().build();
        }
        for (UUID orderedProductLocation : orderedProductLocations) {
            StockId stockId = StockId.builder().locationId(orderedProductLocation)
                    .productId(UUID.fromString(orderProductDto.getProductId()))
                    .build();
            Stock stock = stockRepository.findById(stockId).get();
            if (maximumStock.getQuantity() < stock.getQuantity()) maximumStock = stock;
        }
        return locationRepository.findById(maximumStock.getId().getLocationId()).get();
    }

    @Override
    public List<Location> getLocation(CreateOrderDto createOrderDto) throws BadRequestException {
        List<OrderProductDto> createOrderDtoList = createOrderDto.getOrderProductDtoList();
        List<Location>locationList=new ArrayList<>();
        for (OrderProductDto orderProductDto : createOrderDtoList) {
            List<UUID> orderedProductLocations = stockRepository.findSuitableLocation(UUID.fromString(orderProductDto.getProductId()),
                    orderProductDto.getQuantity());
            Location location=this.getMaximumStockLocation(orderedProductLocations,orderProductDto);
            if (location.getLocationId()!=null) {
                locationList.add(location);
            }
        }
        return locationList;
    }
}
