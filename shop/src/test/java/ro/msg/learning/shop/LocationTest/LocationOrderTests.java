package ro.msg.learning.shop.LocationTest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.domain.key.StockId;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderProductDto;
import ro.msg.learning.shop.exception.BadRequestException;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.strategy.MostAbundantLocation;
import ro.msg.learning.shop.strategy.SingleLocationOrder;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static ro.msg.learning.shop.message.Messages.BAD_PRODUCT;
import static ro.msg.learning.shop.message.Messages.BAD_USER;

@RequiredArgsConstructor
@Service
@ExtendWith(MockitoExtension.class)
public class LocationOrderTests {

    @Mock
    private StockRepository stockRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private SingleLocationOrder singleLocationOrder;
    @InjectMocks
    private MostAbundantLocation mostAbundantLocation;
    @InjectMocks CustomerService customerService;
    @BeforeEach
    public void initUseCase(){
        singleLocationOrder=new SingleLocationOrder(stockRepository,locationRepository);
        mostAbundantLocation= new MostAbundantLocation(stockRepository,locationRepository);
    }

    @Test
    void SingleLocationOrderedSuccessfully() {
        List<OrderProductDto> orderProductDtoList =new ArrayList<>();
        Product product=Product.builder().productId(UUID.randomUUID())
                .build();
        Location location=Location.builder().locationId(UUID.randomUUID()).build();
        StockId stockId=new StockId(product.getProductId(),location.getLocationId());
        Stock stock=Stock.builder()
                .Id(stockId)
                .quantity(5)
                .build();
        orderProductDtoList.add(OrderProductDto.builder()
                .quantity(1)
                .productId(product.getProductId().toString())
                .build());
        Customer customer=Customer.builder().customerId(UUID.randomUUID()).build();
        customer.setOrder(new ArrayList<Order>());
        OrderDto orderDto=OrderDto.builder().customerId(customer.getCustomerId().toString()).build();
        CreateOrderDto createOrderDto= CreateOrderDto.builder()
                .orderProductDtoList(orderProductDtoList)
                .customerId(customer.getCustomerId().toString())
                .build();
        List<UUID>firstProductLocations=new ArrayList<>();
        firstProductLocations.add(location.getLocationId());
        List<Location>locationList=new ArrayList<>();
        locationList.add(location);
        lenient().when(stockRepository.findSuitableLocation(product.getProductId(),orderProductDtoList.get(0).getQuantity())).thenReturn(firstProductLocations);
        lenient().when(locationRepository.findById(location.getLocationId())).thenReturn(Optional.of(location));
        assertEquals(singleLocationOrder.getLocation(createOrderDto),locationList);
    }


    @Test
    void MostAbundantProductHasBeenOrderedSuccessfully() {
        List<OrderProductDto> orderProductDtoList =new ArrayList<>();
        Product product=Product.builder().productId(UUID.randomUUID())
                .build();
        Location location=Location.builder().locationId(UUID.randomUUID()).build();
        StockId stockId=new StockId(product.getProductId(),location.getLocationId());
        Stock stock=Stock.builder()
                .Id(stockId)
                .quantity(5)
                .build();
        orderProductDtoList.add(OrderProductDto.builder()
                .quantity(1)
                .productId(product.getProductId().toString())
                .build());
        Customer customer=Customer.builder().customerId(UUID.randomUUID()).build();
        customer.setOrder(new ArrayList<Order>());
        OrderDto orderDto=OrderDto.builder().customerId(customer.getCustomerId().toString()).build();
        CreateOrderDto createOrderDto= CreateOrderDto.builder()
                .orderProductDtoList(orderProductDtoList)
                .customerId(customer.getCustomerId().toString())
                .build();
        List<UUID>firstProductLocations=new ArrayList<>();
        firstProductLocations.add(location.getLocationId());
        List<Location>locationList=new ArrayList<>();
        locationList.add(location);
        lenient().when(stockRepository.findSuitableLocation(product.getProductId(),orderProductDtoList.get(0).getQuantity())).thenReturn(firstProductLocations);
        lenient().when(locationRepository.findById(location.getLocationId())).thenReturn(Optional.of(location));
        lenient().when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        assertEquals(mostAbundantLocation.getLocation(createOrderDto),locationList);
    }
}
