package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.StockRepository;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    private final StockRepository stockRepository;

    private final OrderDetailRepository orderDetailRepository;


    private final LocationRepository locationRepository;

    public OrderService(OrderRepository orderRepository, StockRepository stockRepository, OrderDetailRepository orderDetailRepository,LocationRepository locationRepository){
        this.orderRepository=orderRepository;
        this.orderDetailRepository=orderDetailRepository;
        this.locationRepository=locationRepository;
        this.stockRepository=stockRepository;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        return null;
    }
}
