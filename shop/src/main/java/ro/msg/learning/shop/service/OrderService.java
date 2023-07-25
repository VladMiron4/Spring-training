package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.domain.key.OrderDetailId;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderProductDto;
import ro.msg.learning.shop.exception.BadRequestException;
import ro.msg.learning.shop.mapper.OrderDetailMapper;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.OrderLocationStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static ro.msg.learning.shop.message.Messages.*;
import static ro.msg.learning.shop.message.Messages.BAD_PRODUCT;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private StockRepository stockRepository;
    private ProductRepository productRepository;
    private OrderDetailMapper orderDetailMapper;
    private OrderLocationStrategy orderLocationStrategy;
    private OrderMapper orderMapper;
    private StockMapper stockMapper;

    public OrderDto create(CreateOrderDto createOrderDto) {
        if (customerRepository.findById(UUID.fromString(createOrderDto.getCustomerId())).isEmpty()) {
            throw new BadRequestException(BAD_USER);
        }

        if (productRepository.findById(UUID.fromString(createOrderDto.getOrderProductDtoList().get(0).getProductId())).isEmpty()) {
            throw new BadRequestException(BAD_PRODUCT);
        }

        for (int i = 0; i < createOrderDto.getOrderProductDtoList().size(); i++) {
            if (createOrderDto.getOrderProductDtoList().get(i).getQuantity() < 0) {
                throw new BadRequestException(NEGATIVE_QUANTITY_EXCEPTION);
            }
        }
        for (int i = 0; i < createOrderDto.getOrderProductDtoList().size(); i++) {
            if (productRepository.findById(UUID.fromString(createOrderDto.getOrderProductDtoList().get(i).getProductId())).isEmpty()) {
                throw new BadRequestException(BAD_PRODUCT);
            }
        }
        OrderDto orderDto=orderMapper.mapCreateOrderToDto(createOrderDto);
        List<OrderProductDto>orderProductDtoList = createOrderDto.getOrderProductDtoList();
        orderDto.setDate(LocalDate.now());
        List<Location> locations= orderLocationStrategy.getLocation(createOrderDto);
        Customer customer = customerRepository.findById(UUID.fromString(createOrderDto.getCustomerId())).get();
        List<OrderDetail> orderDetails = new ArrayList<>();
        Order order = orderMapper.toEntity(orderDto);
        List<Pair<OrderProductDto,Location>>productLocationPairList;
        if (locations.size()==1){
            productLocationPairList= orderProductDtoList
                    .stream().
                    map(orderProductDto -> Pair.of(orderProductDto, locations.get(0))).toList();
        }
        else {
            productLocationPairList=IntStream.range(0,orderProductDtoList.size())
                    .mapToObj(i->Pair.of(orderProductDtoList.get(i),locations.get(i))).toList();
        }
        for (Pair<OrderProductDto, Location> orderProductDtoLocationPair : productLocationPairList) {
            OrderProductDto orderProductDto=orderProductDtoLocationPair.getLeft();
            Location location=orderProductDtoLocationPair.getRight();
            OrderDetailId orderDetailId=orderDetailMapper.mapOrderDetailId(orderProductDto.getProductId(),null);
            Product product=productRepository.findById(UUID.fromString(orderProductDto.getProductId())).get();
            OrderDetail orderDetail=orderDetailMapper.mapOrderDetail(orderDetailId,orderProductDto.getQuantity(),location.getLocationId(),order,product);
            orderDetails.add(orderDetail);
            StockId stockId  = stockMapper.mapStockId(location.getLocationId(),orderProductDto.getProductId());
            Stock stock = stockRepository.findById(stockId).get();
            stock.setQuantity(stock.getQuantity() - orderProductDto.getQuantity());
            stockRepository.save(stock);
        }
        order.setOrderDetail(orderDetails);
        order.setCustomer(customer);
        orderRepository.save(order);
        orderDto.setOrderId(order.getOrderId().toString());
        customer.getOrder().add(orderMapper.toEntity(orderDto));
        customerRepository.save(customer);
        return orderDto;
    }

    public List<OrderDto> findAll() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> foundOrders=orderRepository.findAll();
        foundOrders.forEach(order -> {
            orderDtoList.add(orderMapper.toDto(order));
        });
        return orderDtoList;
    }
}
