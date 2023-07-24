package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.key.OrderDetailId;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.NegativeQuantityException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@ConditionalOnProperty(name = "${strategy}", havingValue = "abundant")
public class MostAbundantLocation implements OrderLocationStrategy {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDto create(CreateOrderDto createOrderDto) throws NegativeQuantityException, ProductNotFoundException, LocationNotFoundException {

        for (int i = 0; i < createOrderDto.getOrderProductDtoList().size(); i++) {
            if (createOrderDto.getOrderProductDtoList().get(i).getQuantity() < 0) {
                throw new NegativeQuantityException();
            }
        }
        OrderDto orderDto = OrderDto.builder()
                .date(LocalDate.now())
                .addressCity(createOrderDto.getAddressCity())
                .addressCountry(createOrderDto.getAddressCountry())
                .adressStreet(createOrderDto.getAddressStreet())
                .adressCounty(createOrderDto.getAddressCounty())
                .customerId(createOrderDto.getCustomerId())
                .build();
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));

        for (int i = 0; i < createOrderDto.getOrderProductDtoList().size(); i++) {
            if (productRepository.findById(createOrderDto.getOrderProductDtoList().get(i).getProductId()).isEmpty()) {
                throw new ProductNotFoundException();
            }

            List<UUID> orderedProductLocations = stockRepository.findSuitableLocation(createOrderDto.getOrderProductDtoList().get(i).getProductId(), createOrderDto.getOrderProductDtoList().get(i).getQuantity());
            Stock maximumStock = Stock.builder().quantity(0).build();
            for (UUID orderedProductLocation : orderedProductLocations) {
                StockId stockId = StockId.builder().locationId(orderedProductLocation)
                        .productId(createOrderDto.getOrderProductDtoList()
                                .get(i)
                                .getProductId())
                        .build();
                Stock stock = stockRepository.findById(stockId).get();
                if (maximumStock.getQuantity() < stock.getQuantity()) maximumStock = stock;
            }
            OrderDetailId orderDetailId = OrderDetailId.builder()
                    .orderId(order.getOrderId())
                    .productId(createOrderDto.getOrderProductDtoList().get(i).getProductId())
                    .build();
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderDetailId(orderDetailId)
                    .quantity(createOrderDto.getOrderProductDtoList().get(i).getQuantity())
                    .locationId(maximumStock.getId().getLocationId())
                    .order(order)
                    .product(productRepository.findById(maximumStock.getId().getProductId()).get())
                    .build();
            orderDetailRepository.save(orderDetail);
            maximumStock.setQuantity(maximumStock.getQuantity() - createOrderDto.getOrderProductDtoList().get(i).getQuantity());
            stockRepository.save(maximumStock);
        }
        orderDto.setOrderId(order.getOrderId());
        return orderDto;
    }
}
