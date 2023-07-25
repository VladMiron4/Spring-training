package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.domain.key.OrderDetailId;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderProductDto;
import ro.msg.learning.shop.exception.BadRequestException;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.mapper.OrderDetailMapper;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ro.msg.learning.shop.message.Messages.*;


@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "${strategy}", havingValue = "abundant")
public class SingleLocationOrder implements OrderLocationStrategy {

    private final StockRepository stockRepository;
    private final LocationRepository locationRepository;



    @Override
    public List<Location> getLocation(CreateOrderDto createOrderDto) throws BadRequestException {
        OrderProductDto firstProduct = createOrderDto.getOrderProductDtoList().get(0);
        List<UUID> firstProductLocations = stockRepository.findSuitableLocation(UUID.fromString(firstProduct.getProductId()), firstProduct.getQuantity());
        List<OrderProductDto> orderProductDtos = createOrderDto.getOrderProductDtoList();
        for (OrderProductDto orderProductDto : orderProductDtos) {
            List<UUID> orderedProductLocations = stockRepository.findSuitableLocation(UUID.fromString(orderProductDto.getProductId()), orderProductDto.getQuantity());
            firstProductLocations.retainAll(orderedProductLocations);
        }

        List<Location> locationList=new ArrayList<>();
        UUID locationId = firstProductLocations.get(0);
        Location location=locationRepository.findById(locationId).get();
        locationList.add(location);
        return locationList;
    }
}
