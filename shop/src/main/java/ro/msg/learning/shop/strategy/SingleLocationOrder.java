package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
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
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "${strategy}", havingValue = "abundant")
public class SingleLocationOrder implements OrderLocationStrategy {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderDto create(CreateOrderDto createOrderDto) throws NegativeQuantityException, ProductNotFoundException, LocationNotFoundException {
        List<UUID> firstProductLocations = stockRepository.findSuitableLocation(createOrderDto.getOrderProductDtoList().get(0).getProductId(), createOrderDto.getOrderProductDtoList().get(0).getQuantity());

        for (int i = 0; i < createOrderDto.getOrderProductDtoList().size(); i++) {
            if (createOrderDto.getOrderProductDtoList().get(i).getQuantity() < 0) {
                throw new NegativeQuantityException();
            }
        }
        for (int i = 1; i < createOrderDto.getOrderProductDtoList().size(); i++) {
            if (productRepository.findById(createOrderDto.getOrderProductDtoList().get(i).getProductId()).isEmpty()) {
                throw new ProductNotFoundException();
            }
            List<UUID> orderedProductLocations = stockRepository.findSuitableLocation(createOrderDto.getOrderProductDtoList().get(i).getProductId(), createOrderDto.getOrderProductDtoList().get(i).getQuantity());
            firstProductLocations.retainAll(orderedProductLocations);
        }
        if (firstProductLocations.isEmpty()) throw new LocationNotFoundException();
        OrderDto orderDto = OrderDto.builder()
                .date(LocalDate.now())
                .addressCity(createOrderDto.getAddressCity())
                .addressCountry(createOrderDto.getAddressCountry())
                .adressStreet(createOrderDto.getAddressStreet())
                .adressCounty(createOrderDto.getAddressCounty())
                .customerId(createOrderDto.getCustomerId())
                .build();
        UUID locationId = firstProductLocations.get(0);
        List<OrderDetail>orderDetails=new ArrayList<>();
        Order order = orderMapper.toEntity(orderDto);

        createOrderDto.getOrderProductDtoList().forEach(orderProductDto -> {
            OrderDetailId orderDetailId = OrderDetailId.builder()
                    .orderId(null)
                    .productId(orderProductDto.getProductId())
                    .build();
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderDetailId(orderDetailId)
                    .quantity(orderProductDto.getQuantity())
                    .locationId(locationId)
                    .order(order)
                    .product(productRepository.findById(orderProductDto.getProductId()).get())
                    .build();
            orderDetails.add(orderDetail);
            StockId stockId = StockId.builder().locationId(locationId).productId(orderProductDto.getProductId()).build();
            Stock stock = stockRepository.findById(stockId).get();
            stock.setQuantity(stock.getQuantity() - orderProductDto.getQuantity());
            stockRepository.save(stock);
        });
        order.setOrderDetail(orderDetails);
        orderRepository.save(order);
        orderDto.setOrderId(order.getOrderId());
        return orderDto;
    }
}
