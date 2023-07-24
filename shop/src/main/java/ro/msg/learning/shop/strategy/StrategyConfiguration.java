package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.StrategyNotFoundException;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

@Configuration
public class StrategyConfiguration {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private OrderLocationStrategy orderLocationStrategy;

    @Value("${strategy}")
    private String strategy;

    public StrategyConfiguration(StockRepository stockRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderMapper orderMapper) throws StrategyNotFoundException {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;

    }

    @Bean
    public OrderLocationStrategy configure() throws StrategyNotFoundException {
        if (!strategy.equals("single") && !strategy.equals("abundant")) {
            throw new StrategyNotFoundException();
        }
        if (strategy.equals("single")) {
            orderLocationStrategy = new SingleLocationOrder(stockRepository, productRepository, orderRepository, orderMapper);
        } else {
            orderLocationStrategy = new MostAbundantLocation(stockRepository, productRepository, orderRepository, orderMapper);
        }
        return orderLocationStrategy;
    }
}
